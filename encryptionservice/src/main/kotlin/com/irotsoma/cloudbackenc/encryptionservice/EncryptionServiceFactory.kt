package com.irotsoma.cloudbackenc.encryptionservice

/**
 * Created by irotsoma on 8/18/2016.
 */
interface EncryptionServiceFactory {
    val encryptionServiceKeyService: EncryptionServiceKeyService
    val encryptionServiceFileService: EncryptionServiceFileService
    val encryptionServiceStringService: EncryptionServiceStringService
}