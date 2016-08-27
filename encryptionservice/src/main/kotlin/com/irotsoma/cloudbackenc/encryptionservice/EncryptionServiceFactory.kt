package com.irotsoma.cloudbackenc.encryptionservice

/**
 * Created by irotsoma on 8/18/2016.
 *
 * Encryption Service Factory interface
 */
interface EncryptionServiceFactory {
    val supportedKeyAlgorithms: Array<EncryptionServiceKeyAlgorithms>
    val supportedEncryptionAlgorithms: Array<EncryptionServiceEncryptionAlgorithms>
    val encryptionServiceKeyService: EncryptionServiceKeyService
    val encryptionServiceFileService: EncryptionServiceFileService
    val encryptionServiceStringService: EncryptionServiceStringService
}