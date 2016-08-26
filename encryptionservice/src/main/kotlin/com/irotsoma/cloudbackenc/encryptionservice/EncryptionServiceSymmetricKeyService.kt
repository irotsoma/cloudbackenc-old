package com.irotsoma.cloudbackenc.encryptionservice

import java.security.SecureRandom
import javax.crypto.SecretKey

/**
 * Created by irotsoma on 8/18/2016.
 */
interface EncryptionServiceSymmetricKeyService : EncryptionServiceKeyService {
    fun generateSymmetricKey(): SecretKey?
    fun generateSymmetricKey(algorithm: String, keySize: Int): SecretKey?
    fun generateSymmetricKey(algorithm: String, keySize: Int, secureRandom: SecureRandom): SecretKey?
}