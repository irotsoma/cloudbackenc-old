package com.irotsoma.cloudbackenc.cloudservice

/**
 * Created by irotsoma on 7/13/2016.
 *
 * Object representing a cloud service user for APIs
 * @param userId User ID for the service
 * @param password Password for the service (only for login, should be blank otherwise)
 * @param state Current state of the login process for this user
 * @param authorizationCallbackURL Use only if state = AWAITING_AUTHORIZATION.  A browser with this URL should be opened for the user to authorize the service.
 */
class CloudServiceUser(val userId: String, val password:String, val serviceUUID: String, val state: STATE , val authorizationCallbackURL: String){
    enum class STATE{
        INITIALIZED,
        CANCELLED,
        ERROR,
        LOGGED_IN,
        LOGGED_OUT,
        AWAITING_AUTHORIZATION
    }
}