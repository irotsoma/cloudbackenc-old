package com.irotsoma.cloudbackenc.cloudservice

/**
* Created by irotsoma on 6/19/2016.
 *
 * Cloud Service Authentication interface
*/
interface AuthenticationService {
    val serviceName : String
    fun login(username: String, password: String) : String
    fun logoff() : String
}