package com.irotsoma.cloudbackenc.centralcontroller

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import java.io.File
import java.io.FileFilter
import java.io.FilenameFilter
import java.net.URL
import java.net.URLClassLoader
import java.util.logging.Logger

/**
 * Created by irotsoma on 6/20/2016.
 */
class CloudServiceLoader : ApplicationContextAware {
    val LOG = Logger.getLogger(this.javaClass.name)
    init{
        loadDynamicServices()
    }
    var _applicationContext : ApplicationContext? = null
    override fun setApplicationContext(applicationContext: ApplicationContext?) {
        _applicationContext = applicationContext
    }

    fun loadDynamicServices() {
        val extensionsDirectory: File = File(CLOUD_SERVICES_DIRECTORY)
        if (!extensionsDirectory.isDirectory || !extensionsDirectory.canRead()) {
            LOG.warning("Extensions directory is missing or unreadable. ${extensionsDirectory.absolutePath}")
            return
        }
        //find all zip files and extract them to the appropriate directory
        for (extFile in extensionsDirectory.listFiles({f:File -> (!f.isDirectory)&&(f.name.endsWith(".zip"))})) {
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

        for (extDir in extensionsDirectory.listFiles({f:File -> f.isDirectory})) {
            var jars : Array<File>? = extDir.listFiles({f:File -> f.name.endsWith(".jar")})
            //TODO: find jar and spring xml and add details to a global var


        }
    }


}