package com.irotsoma.cloudbackenc.cloudservice

/**
 * Created by irotsoma on 7/8/2016.
 *
 * Cloud Service Extension configuration class populated by cloud-service-extension.json from the extension's resources
 * @param serviceName Human readable name of service
 * @param serviceUUID Internal UUID of the service
 * @param packageName Full package name of the factory class for the service
 * @param factoryClass Name of the factory class for the service
 */
data class CloudServiceExtensionConfig(val serviceUUID: String, val serviceName: String, val packageName: String, val factoryClass: String)

