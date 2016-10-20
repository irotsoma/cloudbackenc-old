package com.irotsoma.cloudbackenc.common.encryptionservice

/**
 * A list of symmetric encryption key algorithms that extensions can support.
 *
 * @author Justin Zak
 * @param value The value should be the standard string representations of the algorithms.
 */
enum class EncryptionServiceAsymmetricKeyAlgorithms(
        /**
         * The value should be the standard string representations of the algorithms.
         */
        val value: String) {
    /**
     * RSA encryption key algorithm
     */
    RSA("RSA"){
        override fun validKeyLengths(): List<Int>{
            return listOf(1024, 2048, 3072, 4096)
        }
    },
    /**
     * Elliptic Curve Digital Signature (ECDSA) encryption key algorithm
     *
     * Note: ECDSA key length is determined by the curve type used
     */
    ECDSA("ECDSA"){
        override fun validKeyLengths(): List<Int> {
            return emptyList()
        }
    };

    /**
     * The list of valid key strengths in bits
     *
     * Suggest to be put in ascending numeric order.  And for algorithms with ranges, use steps.  For example RSA is 1024-4096, so I've used steps of 1024 to simplify things.
     *
     * @return A list of integer values that the user should be allowed to select for the key size of the algorithm.
     */
    abstract fun validKeyLengths() : List<Int>
}