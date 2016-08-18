package com.irotsoma.cloudbackenc.centralcontroller.encryption

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration


/**
 * Created by irotsoma on 8/18/2016.
 *
 * Configuration object for encryption services.
 * Loads application.properties settings that start with "encryptionservices".
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("encryptionservices")
open class EncryptionServicesSettings {
    lateinit var directory: String
    lateinit var configFileName: String
}