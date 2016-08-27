package com.irotsoma.cloudbackenc.encryptionservice

/**
 * Created by irotsoma on 8/18/2016.
 *
 * Encryption Service Extension configuration class populated by encryption-service-extension.json from the extension's resources
 * @param serviceName Human readable name of service
 * @param serviceUUID Internal UUID of the service
 * @param packageName Full package name of the factory class for the service
 * @param factoryClass Name of the factory class for the service
 */
data class EncryptionServiceExtensionConfig (val serviceUUID: String, val serviceName: String, val packageName: String, val factoryClass: String)