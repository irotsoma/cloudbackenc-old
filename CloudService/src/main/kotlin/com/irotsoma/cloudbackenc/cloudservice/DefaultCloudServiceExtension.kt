package com.irotsoma.cloudbackenc.cloudservice

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceExtension
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceFactory
import org.springframework.context.ApplicationContextAware
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.ApplicationContext

/**
 * Created by irotsoma on 6/20/2016.
 */
public class DefaultCloudServiceExtension (cloudServiceFactory: CloudServiceFactory) : CloudServiceExtension, ApplicationContextAware, InitializingBean {


    override fun setApplicationContext(applicationContext: ApplicationContext?) {
        throw UnsupportedOperationException()
    }

    override fun afterPropertiesSet() {
        throw UnsupportedOperationException()
    }

    override fun getCloudServiceFactory(): CloudServiceFactory {
        throw UnsupportedOperationException()
    }
}