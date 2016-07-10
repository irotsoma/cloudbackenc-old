package com.irotsoma.cloudbackenc.centralcontroller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.fasterxml.jackson.module.kotlin.readValue
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceException
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceExtensionConfig
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component
import java.io.File
import java.net.URL
import java.net.URLClassLoader
import java.util.*
import java.util.jar.JarFile

/**
 * Created by irotsoma on 6/20/2016.
 */
@Component
open class CloudServiceLoader : ApplicationContextAware {
    companion object { val LOG by logger() }
    @Autowired lateinit var cloudServicesSettings: CloudServicesSettings

    lateinit var _applicationContext : ConfigurableApplicationContext
    override fun setApplicationContext(applicationContext: ApplicationContext?) {

        _applicationContext = applicationContext as ConfigurableApplicationContext? ?: throw CloudServiceException("Application context in CloudServiceLoader is null.")
    }

    fun loadDynamicServices() {
        val cloudServicesSettings: CloudServicesSettings = _applicationContext.getBean(CloudServicesSettings::class.java)
        val extensionsDirectory: File = File(cloudServicesSettings.directory)
        if (!extensionsDirectory.isDirectory || !extensionsDirectory.canRead()) {
            LOG.warn("Extensions directory is missing or unreadable. ${extensionsDirectory.absolutePath}")
            return
        }
        //find all zip files and extract them to the appropriate directory
        for (extFile in extensionsDirectory.listFiles{directory, name -> (!File(directory,name).isDirectory && name.endsWith(".zip"))} ?: arrayOf<File>()){
            val extDir = File(extensionsDirectory, extFile.nameWithoutExtension)
            //if directory already exists, remove it
            if (extDir.exists() && extDir.isDirectory){
                if (extDir.deleteRecursively()) {
                    LOG.warn("Unable to remove existing extension directory: ${extDir.absolutePath}")
                    return
                }
            }
            Unzip().unZipAllToFolder(extFile,extDir)
        }

        var jarURLs = arrayOf<URL>()
        var factoryClasses = mapOf<UUID,String>()



        for (jar in extensionsDirectory.listFiles{directory, name -> (!File(directory,name).isDirectory && name.endsWith(".jar"))} ?: arrayOf<File>()) {
            try {
                val jarFile = JarFile(jar)
                //read config file from jar if present
                var jarFileEntry = jarFile.getEntry(cloudServicesSettings.configFileName)
                if (jarFileEntry == null) {
                    LOG.warn("Cloud service extension missing config file named ${cloudServicesSettings.configFileName}. Skipping jar file: ${jar.absolutePath}")
                }
                else {
                    //get Json config file data
                    val jsonValue = jarFile.getInputStream(jarFileEntry).reader().readText()
                    val mapper = ObjectMapper().registerModule(KotlinModule())
                    val mapperData: CloudServiceExtensionConfig = mapper.readValue(jsonValue)

                    //add values to maps for consumption later
                    var cloudServiceUUID = UUID.fromString(mapperData.serviceUUID)
                    factoryClasses = factoryClasses.plus(Pair(cloudServiceUUID,mapperData.packageName+"."+mapperData.factoryClass))
                    cloudServiceNames = cloudServiceNames.plus(Pair(cloudServiceUUID,mapperData.serviceName))
                    jarURLs = jarURLs.plus(jar.toURI().toURL())
                }
            } catch (e: MissingKotlinParameterException) {
                LOG.warn("Cloud service extension file is missing a required field.  File Name: ${jar.absolutePath}. Error Message: ${e.message}")
            } catch (e: Exception) {
                LOG.warn("Error processing cloud service extension file: ${jar.absolutePath}. Error Message: ${e.message}")
            }
        }

        //create a class loader with all of the jars
        val classLoader = URLClassLoader(jarURLs,_applicationContext.classLoader)
        //cycle through all of the classes, make sure they inheritors CloudServiceFactory, and add them to the list
        for ((key, value) in factoryClasses) {
            val gdClass = classLoader.loadClass(value)
            if (gdClass.newInstance() is CloudServiceFactory) {
                cloudServiceExtensions = cloudServiceExtensions.plus(Pair(key, gdClass as Class<CloudServiceFactory>))
            }
            else {
                LOG.warn("Error loading cloud service extension: Factory is not an instance of CloudServiceFactory: $value" )
            }
        }
    }


}

