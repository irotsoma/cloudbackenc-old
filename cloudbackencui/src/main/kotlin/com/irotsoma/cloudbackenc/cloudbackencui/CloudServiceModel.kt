package com.irotsoma.cloudbackenc.cloudbackencui

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceExtension
import tornadofx.ViewModel
import tornadofx.observable

/**
 * Created by justin on 7/27/2016.
 */
class CloudServiceModel(var source: CloudServiceExtension) : ViewModel() {
    val uuid = bind { source.observable(CloudServiceExtension::uuid)  }
    val name = bind { source.observable(CloudServiceExtension::name) }
    val token = bind { source.observable(CloudServiceExtension::token) }
}