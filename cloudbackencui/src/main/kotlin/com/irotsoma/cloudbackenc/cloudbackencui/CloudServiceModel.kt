package com.irotsoma.cloudbackenc.cloudbackencui

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceExtension
import javafx.beans.property.Property
import tornadofx.ViewModel
import tornadofx.observable
import java.util.*

/**
* Created by irotsoma on 7/27/2016.
*/
class CloudServiceModel(var source: CloudServiceExtension) : ViewModel() {
    val uuid: Property<UUID> = bind { source.observable(CloudServiceExtension::uuid)  }
    val name: Property<String> = bind { source.observable(CloudServiceExtension::name) }
    val token: Property<String> = bind { source.observable(CloudServiceExtension::token) }
}