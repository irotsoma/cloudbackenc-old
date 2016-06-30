package com.irotsoma.cloudbackenc.cloudservice

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware


/**
 * Created by irotsoma on 6/20/2016.
 *
 * Cloud Service Factory interface
 */

interface CloudServiceFactory : ApplicationContextAware {

     val serviceName: String
     var authenticationService: CloudServiceAuthenticationService
     var cloudServiceFileIOService: CloudServiceFileIOService
}