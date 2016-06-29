package com.irotsoma.cloudbackenc.cloudservice

import org.springframework.stereotype.Service

/**
* Created by irotsoma on 6/19/2016.
 *
 * Cloud Service Authentication interface
*/

interface AuthenticationService {

    fun login(username: String, password: String) : String
    fun logoff() : String
}