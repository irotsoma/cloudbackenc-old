package com.irotsoma.cloudbackenc.centralcontroller.encryption

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.fasterxml.jackson.module.kotlin.readValue
import com.irotsoma.cloudbackenc.common.logger
import com.irotsoma.cloudbackenc.encryptionservice.*
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
import javax.annotation.PostConstruct

/**
 * Created by irotsoma on 8/18/2016.
 *
 * Imports and stores information about installed Encryption Service Extensions
 */
@Component
open class EncryptionServiceRepository : ApplicationContextAware {
    companion object { val LOG by logger() }
    //inject settings
    @Autowired lateinit var encryptionServicesSettings: EncryptionServicesSettings
    var encryptionServiceExtensions = emptyMap<UUID,Class<EncryptionServiceFactory>>()
    var encryptionServiceNames = EncryptionServiceExtensionList()
    //application context must be set before
    lateinit var _applicationContext : ConfigurableApplicationContext
    override fun setApplicationContext(applicationContext: ApplicationContext?) {
        _applicationContext = applicationContext as ConfigurableApplicationContext? ?: throw EncryptionServiceException("Application context in EncryptionServiceRepository is null.")
    }

    @PostConstruct
    fun loadDynamicServices() {
        val extensionsDirectory: File = File(encryptionServicesSettings.directory)
        if (!extensionsDirectory.isDirectory || !extensionsDirectory.canRead()) {
            LOG.warn("Extensions directory is missing or unreadable. ${extensionsDirectory.absolutePath}")
            return
        }
        var jarURLs = emptyArray<URL>()
        var factoryClasses = emptyMap<UUID,String>()

        for (jar in extensionsDirectory.listFiles{directory, name -> (!File(directory,name).isDirectory && name.endsWith(".jar"))} ?: arrayOf<File>()) {
            try {
                val jarFile = JarFile(jar)
                //read config file from jar if present
                val jarFileEntry = jarFile.getEntry(encryptionServicesSettings.configFileName)
                if (jarFileEntry == null) {
                    LOG.debug("Extension file missing config file named ${encryptionServicesSettings.configFileName}. Skipping jar file: ${jar.absolutePath}")
                }
                else {
                    //get Json config file data
                    val jsonValue = jarFile.getInputStream(jarFileEntry).reader().readText()
                    val mapper = ObjectMapper().registerModule(KotlinModule())
                    val mapperData: EncryptionServiceExtensionConfig = mapper.readValue(jsonValue)
                    //add values to maps for consumption later
                    val encryptionServiceUUID = UUID.fromString(mapperData.serviceUUID)
                    factoryClasses = factoryClasses.plus(Pair(encryptionServiceUUID,mapperData.packageName+"."+mapperData.factoryClass))
                    encryptionServiceNames.add(EncryptionServiceExtension(encryptionServiceUUID,mapperData.serviceName))
                    jarURLs = jarURLs.plus(jar.toURI().toURL())
                }
            } catch (e: MissingKotlinParameterException) {
                LOG.warn("Encryption service extension configuration file is missing a required field.  This extension will be unavailable: ${jar.name}.  Error Message: ${e.message}")
            } catch (e: Exception) {
                LOG.warn("Error processing encryption service extension file. This extension will be unavailable: ${jar.name}.   Error Message: ${e.message}")
            }
        }
        //create a class loader with all of the jars
        val classLoader = URLClassLoader(jarURLs,_applicationContext.classLoader)
        //cycle through all of the classes, make sure they inheritors EncryptionServiceFactory, and add them to the list
        for ((key, value) in factoryClasses) {
            val gdClass = classLoader.loadClass(value)
            //verify instance of gdClass is a EncryptionServiceFactory
            if (gdClass.newInstance() is EncryptionServiceFactory) {
                //add to list -- suppress warning about unchecked class as we did that in the if statement for an instance but it can't be done directly
                encryptionServiceExtensions = encryptionServiceExtensions.plus(Pair(key, @Suppress("UNCHECKED_CAST")(gdClass as Class<EncryptionServiceFactory>)))
            }
            else {
                LOG.warn("Error loading encryption service extension: Factory is not an instance of EncryptionServiceFactory: $value" )
            }
        }
    }
}
