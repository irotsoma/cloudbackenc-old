package com.irotsoma.cloudbackenc.cloudservice

import org.springframework.context.ApplicationContext
import java.io.File

/**
 * Created by irotsoma on 6/20/2016.
 *
 * Generic bootstrap for cloud service implementations
 */
interface CloudServiceBootstrap  {
    @Throws(CloudServiceException::class)
    fun initialize(parentContext: ApplicationContext, extensionsDirectory: File) : CloudServiceExtension
}