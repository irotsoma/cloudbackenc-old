package com.irotsoma.cloudbackenc.cloudservice

import org.springframework.beans.factory.FactoryBean
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

/**
 * Created by justin.zak on 6/29/2016.
 */
@Component
class CloudServiceLoader<T : CloudServiceFactory>(serviceType : T) : FactoryBean<CloudServiceFactory>, ApplicationContextAware {

    lateinit var _applicationContext : ApplicationContext

    val _serviceType = serviceType

    override fun setApplicationContext(applicationContext: ApplicationContext?) {
        _applicationContext = applicationContext ?: throw CloudServiceException("Missing application context when initializing cloud service factory bean.")
    }

    override fun isSingleton(): Boolean {
        return false
    }

    override fun getObject(): CloudServiceFactory? {
        return _applicationContext.getBean(_serviceType.javaClass)
    }

    override fun getObjectType(): Class<*>? {
        return _serviceType.javaClass
    }





}