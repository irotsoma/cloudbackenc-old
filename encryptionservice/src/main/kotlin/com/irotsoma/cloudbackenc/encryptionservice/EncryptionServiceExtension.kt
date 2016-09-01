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

import java.util.*

/**
 * Created by irotsoma on 8/18/2016.
 *
 * Encryption Service Extension Object
 */
class EncryptionServiceExtension {
    var uuid: UUID = UUID.randomUUID()
    var name: String = ""
    constructor(){}
    constructor(uuid: UUID, name: String){
        this.uuid = uuid
        this.name = name
    }
}
