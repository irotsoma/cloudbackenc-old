package com.irotsoma.cloudbackenc.cloudservice

/**
 * Created by irotsoma on 7/13/2016.
 *
 * Object representing a cloud service user for APIs
 * @param userId User ID for the service
 * @param password Password for the service (only for login, should be blank otherwise)
 * @param token Login token for the service
 */
class CloudServiceUser(val userId: String, val password:String, val serviceUUID: String, val token:String)