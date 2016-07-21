package com.irotsoma.cloudbackenc.centralcontroller

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources


/**
 * Created by irotsoma on 6/28/2016.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("cloudservices")
open class CloudServicesSettings {

    lateinit var directory: String
    lateinit var configFileName: String

}