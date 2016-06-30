package com.irotsoma.cloudbackenc.centralcontroller

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.support.DefaultListableBeanFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner
import org.springframework.stereotype.Component
import java.io.File
import java.net.JarURLConnection
import java.net.URL
import java.net.URLClassLoader
import java.util.jar.Attributes
import java.util.logging.Logger

/**
 * Created by irotsoma on 6/20/2016.
 */
@Component
class CloudServiceLoader_OLD : ApplicationContextAware {
    val LOG = Logger.getLogger(this.javaClass.name)
    @Autowired lateinit var controllerSettings : ControllerSettings

    lateinit var _applicationContext : ApplicationContext
    override fun setApplicationContext(applicationContext: ApplicationContext?) {

        _applicationContext = applicationContext ?: throw CloudServiceException("Application context in CloudServiceLoader is null.")
    }

    fun loadDynamicServices() {
        var controllerSettings : ControllerSettings = _applicationContext.getBean(ControllerSettings::class.java)
        val extensionsDirectory: File = File(controllerSettings.cloudServicesDirectory)
        //val abp = extensionsDirectory.absolutePath
        if (!extensionsDirectory.isDirectory || !extensionsDirectory.canRead()) {
            LOG.warning("Extensions directory is missing or unreadable. ${extensionsDirectory.absolutePath}")
            return
        }
        //find all zip files and extract them to the appropriate directory
        for (extFile in extensionsDirectory.listFiles{directory, name -> (!File(directory,name).isDirectory && name.endsWith(".zip"))} ?: arrayOf<File>()){
        //for (extFile in extensionsDirectory.listFiles({f:File -> (!f.isDirectory)&&(f.name.endsWith(".zip"))})) {
            val extDir = File(extFile.nameWithoutExtension)
            //if directory already exists, remove it
            if (extDir.exists()){
                if (extDir.deleteRecursively()) {
                    LOG.warning("Unable to remove existing extension directory: ${extDir.absolutePath}")
                    return
                }
            }
            //TODO: Unzip

        }


        for (jar in extensionsDirectory.listFiles{directory, name -> (!File(directory,name).isDirectory && name.endsWith(".jar"))} ?: arrayOf<File>()) {
            val jarURL : URL = jar.toURI().toURL()
            var jarConnection : JarURLConnection = jarURL.openConnection() as JarURLConnection
            val attr : Attributes = jarConnection.mainAttributes

        }
    }


}

