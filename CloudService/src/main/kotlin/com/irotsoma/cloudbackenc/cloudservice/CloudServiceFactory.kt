package com.irotsoma.cloudbackenc.cloudservice

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * Created by irotsoma on 6/20/2016.
 *
 * Cloud Service Factory interface
 */
interface CloudServiceFactory {
    val serviceName: String
    var authenticationService: AuthenticationService

    var cloudServiceFileIOService: CloudServiceFileIOService
}