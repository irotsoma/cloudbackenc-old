/*
 * Created by irotsoma on 10/6/16.
 */
package com.irotsoma.cloudbackenc.common

import javax.crypto.SealedObject

interface CloudBackEncUser {
    val userId : String
    val password : SealedObject
    val isActive: Boolean
}