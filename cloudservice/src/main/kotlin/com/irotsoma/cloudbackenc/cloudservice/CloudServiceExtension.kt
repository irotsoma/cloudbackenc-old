package com.irotsoma.cloudbackenc.cloudservice

import java.util.*

/**
* Created by irotsoma on 7/27/2016.
 *
 * Cloud Service Extension object
*/

class CloudServiceExtension {
    var uuid: UUID = UUID.randomUUID()
    var name: String = ""
    var token: String = ""
    constructor(){}
    constructor(uuid: UUID, name: String){
        this.uuid = uuid
        this.name = name
    }
    constructor(uuid: UUID, name: String, token: String){
        this.token = token
        this.uuid = uuid
        this.name = name
    }
}