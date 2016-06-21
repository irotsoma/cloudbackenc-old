/**
 * Created by justin on 6/20/2016.
 */
package com.irotsoma.cloudbackenc.cloudservice

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceBootstrap
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceException
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceExtension
import org.springframework.context.ApplicationContext
import org.springframework.context.support.FileSystemXmlApplicationContext
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource

import java.io.File

/**
 * Created by irotsoma on 6/20/2016.
 *
 * Default implementation of bootstrap class for cloud services
 */
class DefaultCloudServiceBootstrap : CloudServiceBootstrap {

    var applicationContext: FileSystemXmlApplicationContext? = null

    @Throws(CloudServiceException::class)
    override fun initialize(parentContext: ApplicationContext, extensionsDirectory: File): CloudServiceExtension {
        val configFile = File(extensionsDirectory, "resources/main/cloudservice-context.xml")
        if (!configFile.isFile || !configFile.canRead()) {
            throw CloudServiceException("Unable to read configuration file: " + configFile.absolutePath)
        }

        val configLocations = arrayOf(configFile.toURI().toString())
        applicationContext = object : FileSystemXmlApplicationContext(configLocations, true, parentContext) {
            override fun getResourceByPath(path: String): Resource {
                var _path = path
                if (_path.startsWith("/")) {
                    _path = _path.substring(1)
                }
                return FileSystemResource(File(extensionsDirectory, _path))
            }
        }

        return applicationContext!!.getBean(CloudServiceExtension::class.java)
    }
}