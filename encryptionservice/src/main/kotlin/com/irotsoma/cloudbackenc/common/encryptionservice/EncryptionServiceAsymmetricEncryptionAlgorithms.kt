/*
 * Created by irotsoma on 10/20/16.
 */
package com.irotsoma.cloudbackenc.common.encryptionservice

/**
 * A list of encryption algorithms that extensions can support.
 *
 * @author Justin Zak
 * @param value The value should be the standard string representations of the algorithms.
 */
enum class EncryptionServiceAsymmetricEncryptionAlgorithms(
        /**
         * The value should be the standard string representations of the algorithms.
         */
        val value: String) {
    //TODO:  Add more algorithms and remove insecure ones.
    RSA("RSA"),
    RSA_PKCS1("RSA/PKCS1"),
    RSA__PKCS1PADDING("RSA//PKCS1PADDING")

}