package com.irotsoma.cloudbackenc.cloudservice

/**
 * Created by irotsoma on 7/12/2016.
 */
data class CloudServiceExtensionFactory(val uuid: UUID, val serviceFactory : Class<CloudServiceFactory>)