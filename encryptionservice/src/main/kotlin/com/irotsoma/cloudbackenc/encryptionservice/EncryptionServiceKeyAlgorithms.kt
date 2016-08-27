package com.irotsoma.cloudbackenc.encryptionservice

/**
 * Created by irotsoma on 8/26/2016.
 *
 * A list of encryption key algorithms that extensions can support.  The value should be the standard string representations of the algorithms.
 */
enum class EncryptionServiceKeyAlgorithms(val value: String) {
    AES("AES")
}