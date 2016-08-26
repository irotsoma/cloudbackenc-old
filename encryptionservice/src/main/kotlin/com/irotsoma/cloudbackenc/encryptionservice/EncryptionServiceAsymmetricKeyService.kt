package com.irotsoma.cloudbackenc.encryptionservice

import java.security.KeyPair
import java.security.SecureRandom

/**
 * Created by irotsoma on 8/18/2016.
 */
interface EncryptionServiceAsymmetricKeyService : EncryptionServiceKeyService {
    fun generateSymmetricKey(): KeyPair?
    fun generateSymmetricKey(algorithm: String, keySize: Int): KeyPair?
    fun generateSymmetricKey(algorithm: String, keySize: Int, secureRandom: SecureRandom): KeyPair?
}