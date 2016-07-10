package com.irotsoma.cloudbackenc.centralcontroller

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceFactory
import java.util.*

/**
 * Created by irotsoma on 7/9/2016.
 */
var cloudServiceExtensions = mapOf<UUID,Class<CloudServiceFactory>>()
var cloudServiceNames = mapOf<UUID,String>()