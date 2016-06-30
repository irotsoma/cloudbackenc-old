package com.irotsoma.cloudbackenc.cloudservice

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import java.io.File
import java.io.InputStream

/**
 * Created by irotsoma on 6/20/2016.
 */
interface CloudServiceFileIOService {
     var _applicationContext : ApplicationContext

    /*override fun setApplicationContext(applicationContext: ApplicationContext?) {
        _applicationContext = applicationContext ?: throw CloudServiceException("Missing application context when initializing cloud service file I/O service.")
    }*/
    fun upload(filePath: File) : Boolean
    fun list(dirPath: File) : List<File>
    fun download(filePath: File) : InputStream
}