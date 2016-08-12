package com.irotsoma.cloudbackenc.cloudbackencui

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceExtension
import javafx.beans.property.Property
import tornadofx.ViewModel
import tornadofx.observable
import java.util.*

/**
* Created by irotsoma on 7/27/2016.
 *
 * View model class for binding CloudServiceExtension objects to UI components
*/
class CloudServiceModel(var service: CloudServiceExtension) : ViewModel() {
    val uuid: Property<UUID> = bind { service.observable(CloudServiceExtension::uuid)  }
    val name: Property<String> = bind { service.observable(CloudServiceExtension::name) }
    val token: Property<String> = bind { service.observable(CloudServiceExtension::token) }
}