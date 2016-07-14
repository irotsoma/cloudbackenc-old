package com.irotsoma.cloudbackenc.cloudservice

/**
 * Created by irotsoma on 6/20/2016.
 *
 * Cloud Service Factory interface
 */
interface CloudServiceFactory {

     var authenticationService: CloudServiceAuthenticationService
     var cloudServiceFileIOService: CloudServiceFileIOService
}

