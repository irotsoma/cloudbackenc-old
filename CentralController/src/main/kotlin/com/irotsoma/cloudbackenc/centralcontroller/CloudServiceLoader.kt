package com.irotsoma.cloudbackenc.centralcontroller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
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
        //val abp = extensionsDirectory.absolutePath
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

        var jarURLs = kotlin.arrayOfNulls<URL>(0)
        var classNames = kotlin.arrayOfNulls<String>(0)
        var serviceNames = kotlin.arrayOfNulls<String>(0)


        for (jar in extensionsDirectory.listFiles{directory, name -> (!File(directory,name).isDirectory && name.endsWith(".jar"))} ?: arrayOf<File>()) {
            jarURLs = jarURLs.plus(jar.toURI().toURL())
            val jarFile = JarFile(jar)
            var jarFileEntry = jarFile.getEntry(cloudServicesSettings.configFileName)
            val jarInputStream = jarFile.getInputStream(jarFileEntry).reader()
            val jsonValue: String = jarInputStream.readText()
            val mapper = ObjectMapper().registerModule(KotlinModule())
            val mapperData: CloudServiceExtensionConfig = mapper.readValue(jsonValue)

            //TODO: get data from JSON file



            val test = "test"

        }

        val classLoader = URLClassLoader(jarURLs,_applicationContext.classLoader)

        //TODO: load all classes as defined in the json configs if class not found log it and move on
        val gdClass = classLoader.loadClass("com.irotsoma.cloudbackenc.cloudservice.googledrive.GoogleDriveCloudServiceFactory")
        if (gdClass.newInstance() is CloudServiceFactory) {
            cloudServicesSettings.extensions = cloudServicesSettings.extensions.plus(gdClass as Class<CloudServiceFactory>)
        }
    }


}

