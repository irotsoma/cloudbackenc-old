package com.irotsoma.cloudbackenc.centralcontroller.cloudservices

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration


/**
 * Created by irotsoma on 6/28/2016.
 *
 * Configuration object for cloud services.
 * Loads application.properties settings that start with "cloudservices".
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("cloudservices")
open class CloudServicesSettings {

    lateinit var directory: String
    lateinit var configFileName: String

}