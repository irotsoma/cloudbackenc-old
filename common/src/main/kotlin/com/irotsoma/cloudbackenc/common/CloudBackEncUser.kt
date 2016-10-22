/*
 * Created by irotsoma on 10/6/16.
 */
package com.irotsoma.cloudbackenc.common

class CloudBackEncUser(val userId : String,
                       val password : String,
                       val email: String?,
                       val enabled: Boolean,
                       val roles : List<CloudBackEncRoles>){
    companion object{
        const val PASSWORD_MASKED = "PASSWORD_MASKED"
    }
    fun maskedPasswordInstance(): CloudBackEncUser{
        return CloudBackEncUser(userId, PASSWORD_MASKED, email, enabled,roles)
    }
}