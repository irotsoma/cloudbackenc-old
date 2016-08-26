package com.irotsoma.cloudbackenc.encryptionservice

import java.io.InputStream
import java.io.OutputStream
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey

/**
 * Created by irotsoma on 8/18/2016.
 */
interface EncryptionServiceFileService {
    fun encrypt(inputStream: InputStream, outputStream: OutputStream, key: SecretKey, algorithm: String)
    fun encrypt(inputStream: InputStream, outputStream: OutputStream, key: PublicKey, algorithm: String)
    fun decrypt(inputStream: InputStream, outputStream: OutputStream, key: SecretKey, algorithm: String)
    fun decrypt(inputStream: InputStream, outputStream: OutputStream, key: PrivateKey, algorithm: String)
}