package com.irotsoma.cloudbackenc.cloudservice

import java.net.URL

/**
* Created by irotsoma on 6/19/2016.
 *
 * Cloud Service Authentication interface
*/

interface CloudServiceAuthenticationService  {
    var authorizationURL: URL?
    //var authorizationCallbackURL: URL?

    fun isLoggedIn(user: CloudServiceUser): Boolean
    fun login(user: CloudServiceUser) : CloudServiceUser
    fun logoff(user: CloudServiceUser) : String
}