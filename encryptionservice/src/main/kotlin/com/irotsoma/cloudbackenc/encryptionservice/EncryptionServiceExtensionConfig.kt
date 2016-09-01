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

package com.irotsoma.cloudbackenc.encryptionservice

/**
 * Created by irotsoma on 8/18/2016.
 *
 * Encryption Service Extension configuration class populated by encryption-service-extension.json from the extension's resources
 * @param serviceName Human readable name of service
 * @param serviceUUID Internal UUID of the service
 * @param packageName Full package name of the factory class for the service
 * @param factoryClass Name of the factory class for the service
 */
data class EncryptionServiceExtensionConfig (val serviceUUID: String, val serviceName: String, val packageName: String, val factoryClass: String)