package com.irotsoma.cloudbackenc.centralcontroller

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration


/**
 * Created by irotsoma on 6/28/2016.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("controller")
open class ControllerSettings {

    lateinit var cloudServicesDirectory: String
    var plugins : List<Class<CloudServiceFactory>> = emptyList()
}