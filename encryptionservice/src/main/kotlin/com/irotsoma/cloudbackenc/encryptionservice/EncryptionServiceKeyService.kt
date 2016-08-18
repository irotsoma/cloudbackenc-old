package com.irotsoma.cloudbackenc.encryptionservice

/**
 * Created by irotsoma on 8/18/2016.
 */
interface EncryptionServiceKeyService {
    fun generateKeys(): EncryptionServiceKeyRing
}