package com.irotsoma.cloudbackenc.cloudservice

import org.springframework.beans.factory.annotation.Autowired



/**
 * Created by irotsoma on 6/20/2016.
 *
 * Cloud Service Factory interface
 */
open class CloudServiceFactory {
    @Autowired
    lateinit var authenticationService: AuthenticationService
    @Autowired
    lateinit var cloudServiceFileIOService: CloudServiceFileIOService
}