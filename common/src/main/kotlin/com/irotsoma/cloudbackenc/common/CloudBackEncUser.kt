/*
 * Created by irotsoma on 10/6/16.
 */
package com.irotsoma.cloudbackenc.common

class CloudBackEncUser(val userId : String,
                       val password : String,
                       val emailAddress : String?,
                       val active: Boolean,
                       val roles : List<String>){
}