package com.irotsoma.cloudbackenc.cloudservice

import org.springframework.context.ApplicationContext

/**
* Created by irotsoma on 6/19/2016.
 *
 * Cloud Service Authentication interface
*/

interface CloudServiceAuthenticationService  {
    var _applicationContext : ApplicationContext

    /*override fun setApplicationContext(applicationContext: ApplicationContext?) {
        _applicationContext = applicationContext ?: throw CloudServiceException("Missing application context when initializing cloud service authentication service.")
    }*/
    fun login(username: String, password: String) : String
    fun logoff() : String
}