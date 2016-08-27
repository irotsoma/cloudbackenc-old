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
