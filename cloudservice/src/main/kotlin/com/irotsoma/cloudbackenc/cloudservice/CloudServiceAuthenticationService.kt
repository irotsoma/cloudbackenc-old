package com.irotsoma.cloudbackenc.cloudservice

/**
* Created by irotsoma on 6/19/2016.
 *
 * Cloud Service Authentication interface
*/

interface CloudServiceAuthenticationService  {
    fun isLoggedIn(user: CloudServiceUser): Boolean
    fun login(user: CloudServiceUser) : String
    fun logoff(user: CloudServiceUser) : String
}