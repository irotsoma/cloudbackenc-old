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

/*
 * Created by irotsoma on 10/20/16.
 */
package com.irotsoma.cloudbackenc.filecontroller.controllers.exceptions

import com.irotsoma.cloudbackenc.common.RestException
import com.irotsoma.cloudbackenc.common.RestExceptionExceptions

/**
 * Custom exception for trying to access /file-encryptors with an invalid UUID
 */
class InvalidEncryptionServiceUUIDException() : RestException(RestExceptionExceptions.Invalid_Encryption_Service_UUID)

class EncryptionServiceFileNotFoundException(): RestException(RestExceptionExceptions.File_Not_Found)

class UnsupportedEncryptionAlgorithm(): RestException(RestExceptionExceptions.Unsupported_Encryption_Algorithm)