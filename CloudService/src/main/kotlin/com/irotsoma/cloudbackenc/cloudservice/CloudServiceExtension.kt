package com.irotsoma.cloudbackenc.cloudservice
/**
 * Created by irotsoma on 6/20/2016.
 *
 * Cloud Service Extension interface
 */
interface CloudServiceExtension {
    fun getCloudServiceFactory() : CloudServiceFactory
}