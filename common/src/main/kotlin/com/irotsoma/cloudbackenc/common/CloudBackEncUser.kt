/*
 * Created by irotsoma on 10/6/16.
 */
package com.irotsoma.cloudbackenc.common

import javax.crypto.SealedObject

class CloudBackEncUser {
    val userId : String
    val password : SealedObject
    val isActive: Boolean
}