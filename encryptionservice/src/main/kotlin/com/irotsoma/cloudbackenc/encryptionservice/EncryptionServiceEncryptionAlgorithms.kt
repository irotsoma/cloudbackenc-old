package com.irotsoma.cloudbackenc.encryptionservice

/**
 * Created by irotsoma on 8/26/2016.
 *
 * A list of encryption algorithms that extensions can support.  The value should be the standard string representations of the algorithms.
 */
enum class EncryptionServiceEncryptionAlgorithms(val value: String) {
    AES("AES"),
    AES_CBC_PKCS5Padding("AES/CBC/PKCS5Padding")
}