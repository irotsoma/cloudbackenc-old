package com.irotsoma.cloudbackenc.encryptionservice

import java.security.KeyPair
import java.security.SecureRandom
import javax.crypto.SecretKey

/**
 * Created by irotsoma on 8/18/2016.
 *
 * Service for generating keys
 */
interface EncryptionServiceKeyService {
    fun generateSymmetricKey(): SecretKey?
    fun generateSymmetricKey(algorithm: EncryptionServiceKeyAlgorithms, keySize: Int): SecretKey?
    fun generateSymmetricKey(algorithm: EncryptionServiceKeyAlgorithms, keySize: Int, secureRandom: SecureRandom): SecretKey?

    fun generateAsymmetricKeys(): KeyPair?
    fun generateAsymmetricKeys(algorithm: EncryptionServiceKeyAlgorithms, keySize: Int): KeyPair?
    fun generateAsymmetricKeys(algorithm: EncryptionServiceKeyAlgorithms, keySize: Int, secureRandom: SecureRandom): KeyPair?
}