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
 *//*
 * Created by irotsoma on 9/23/2016.
 */

package com.irotsoma.cloudbackenc.common.cloudservice
/**
 * Object that represents a file or directory on a cloud service provider
 *
 * @author Justin Zak
 */
data class CloudServiceFile(
        /**
         * Full path within the cloud service provider used to access the file (including file name)
         */
        val filePath: String,
        /**
         * Name of the file (without path information).
         */
        val fileName: String,
        /**
         * True if object is a directory, otherwise false. (default is false)
         */
        val isDirectory: Boolean = false,
        /**
         * True if the user does not have permission to modify the file, otherwise false. (default is false)
         */
        val isReadOnly: Boolean = false,
        /**
         * True if the user has permission to download the file, otherwise false. (default is true)
         */
        val isDownloadable: Boolean = true,
        /**
         * Cloud Service identifier for a file (if supported)
         */
        val fileID: String? = null,
        /**
         * File size in bytes.
         */
        val size: Long? = 0)