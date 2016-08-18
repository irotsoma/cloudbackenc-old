package com.irotsoma.cloudbackenc.cloudservice

/**
 * Created by irotsoma on 6/20/2016.
 *
 * Cloud Service Factory interface
 */
interface CloudServiceFactory {

     val authenticationService: CloudServiceAuthenticationService
     val cloudServiceFileIOService: CloudServiceFileIOService
}

