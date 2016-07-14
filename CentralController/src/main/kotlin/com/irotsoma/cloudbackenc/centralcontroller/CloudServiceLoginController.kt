package com.irotsoma.cloudbackenc.centralcontroller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceException
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceFactory
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * Created by irotsoma on 7/13/2016.
 */
@RestController
open class CloudServiceLoginController {
    @Autowired
    private lateinit var cloudServiceRepository: CloudServiceRepository

    @RequestMapping("/cloudservice/login/{uuid}", method = arrayOf(RequestMethod.POST))
    fun login(@PathVariable(value="uuid")uuid: String, @RequestBody user: String ) : ResponseEntity<CloudServiceUser> {

        val mapper = ObjectMapper().registerModule(KotlinModule())
        val mapperData: CloudServiceUser = mapper.readValue(user)


        val cloudServiceFactory : Class<CloudServiceFactory> = cloudServiceRepository.cloudServiceExtensions[UUID.fromString(uuid)] ?: throw InvalidPathVariableException("Invalid UUID.")
        var token : String
        try {
            token = cloudServiceFactory.newInstance().authenticationService.login(mapperData.userId, mapperData.password)
        } catch (e:Exception ){
            throw CloudServiceException(e.message)
        }

        return ResponseEntity(CloudServiceUser(userId=mapperData.userId, password = "", token = token),HttpStatus.OK)
    }

}