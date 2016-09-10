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

/**
 * Created by irotsoma on 8/26/2016.
 *
 * A list of encryption algorithms that extensions can support.  The value should be the standard string representations of the algorithms.
 */
enum class EncryptionServiceEncryptionAlgorithms(val value: String) {
    //TODO:  Add more algorithms and remove insecure ones.
    // This is just a quick list of ones supported by bouncycastle/apache commons crypto including some insecure ones for testing.
    AES("AES"),
    AES_CBC_PKCS5Padding("AES/CBC/PKCS5Padding"),
    AES_ECB_WithCTS("AES/ECB/WithCTS"),
    DES_CBC_PKCS5Padding("DES/CBC/PKCS5Padding"),
    DES_ECB_WithCTS("DES/ECB/WithCTS"),
    SKIPJACK_ECB_PKCS7Padding("SKIPJACK/ECB/PKCS7Padding"),
    Twofish_CBC_PKCS5Padding("Twofish/CBC/PKCS5Padding"),
    Blowfish_CBC_PKCS5Padding("Blowfish/CBC/PKCS5Padding")
}