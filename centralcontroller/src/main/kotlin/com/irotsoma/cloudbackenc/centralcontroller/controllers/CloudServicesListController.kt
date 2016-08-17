package com.irotsoma.cloudbackenc.centralcontroller.controllers

import com.irotsoma.cloudbackenc.centralcontroller.cloudservices.CloudServiceRepository
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceExtensionList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody


/**
 * Created by irotsoma on 7/12/2016.
 *
 * REST Controller for getting a list of cloud service extensions currently installed.
 *
 * Use GET method to /cloudservices URL.
 */
@Controller
@RequestMapping("/cloudservices")
open class CloudServicesListController {

    @Autowired
    private lateinit var cloudServiceRepository: CloudServiceRepository

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    @ResponseBody fun getCloudServices() : CloudServiceExtensionList {
        return cloudServiceRepository.cloudServiceNames
    }
}