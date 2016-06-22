package com.irotsoma.cloudbackenc.cloudservice


/**
 * Created by irotsoma on 6/20/2016.
 *
 * Cloud Service Factory interface
 */
open class CloudServiceFactory {

    lateinit var authenticationService: AuthenticationService

    lateinit var cloudServiceFileIOService: CloudServiceFileIOService
}