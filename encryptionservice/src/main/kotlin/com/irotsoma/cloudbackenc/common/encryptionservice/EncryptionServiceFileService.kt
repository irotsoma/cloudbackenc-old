/*
 * Copyright (C) 2016  Irotsoma, LLC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.irotsoma.cloudbackenc.common.encryptionservice

import java.io.InputStream
import java.io.OutputStream
import java.security.PrivateKey
import java.security.PublicKey
import java.security.SecureRandom
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

/**
 * Created by irotsoma on 8/18/2016.
 *
 * Collection of functions for encrypting and decrypting files.
 */
interface EncryptionServiceFileService {
    fun encrypt(inputStream: InputStream, outputStream: OutputStream, key: SecretKey, algorithm: EncryptionServiceEncryptionAlgorithms, ivParameterSpec: IvParameterSpec?, secureRandom: SecureRandom?)
    fun encrypt(inputStream: InputStream, outputStream: OutputStream, key: PublicKey, algorithm: EncryptionServiceEncryptionAlgorithms, ivParameterSpec: IvParameterSpec?, secureRandom: SecureRandom?)
    fun decrypt(inputStream: InputStream, outputStream: OutputStream, key: SecretKey, algorithm: EncryptionServiceEncryptionAlgorithms, ivParameterSpec: IvParameterSpec?, secureRandom: SecureRandom?)
    fun decrypt(inputStream: InputStream, outputStream: OutputStream, key: PrivateKey, algorithm: EncryptionServiceEncryptionAlgorithms, ivParameterSpec: IvParameterSpec?, secureRandom: SecureRandom?)
}