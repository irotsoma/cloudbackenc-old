package com.irotsoma.cloudbackenc.centralcontroller

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceException
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceFactory
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.MalformedURLException
import java.net.URL
import java.util.*

/**
 * Created by irotsoma on 7/13/2016.
 *
 * Rest Controller that takes an instance of CloudServiceUser as JSON, calls the login method of the requested cloud
 * service as identified in the URL by UUID, and returns an instance of CloudServiceUser with the userId and login
 * token.
 */
@RestController
open class CloudServiceLoginController {
    @Autowired
    private lateinit var cloudServiceRepository: CloudServiceRepository

    @RequestMapping("cloudservice/login/{uuid}", method = arrayOf(RequestMethod.POST))
    fun login(@PathVariable(value="uuid")uuid: String, @RequestBody user: CloudServiceUser ) : ResponseEntity<CloudServiceUser> {
        //TODO:  authentication
        val cloudServiceFactory : Class<CloudServiceFactory> = cloudServiceRepository.cloudServiceExtensions[UUID.fromString(uuid)] ?: throw InvalidPathVariableException("Invalid UUID.")
        val state : CloudServiceUser.STATE
        val authenticationService = cloudServiceFactory.newInstance().authenticationService

        try {
            authenticationService.authorizationCallbackURL = URL(user.authorizationURL)
        } catch (e: MalformedURLException){ //ignore and let it default to null if URL is invalid or missing
        }

        try {
            state = authenticationService.login(user)
        } catch (e:Exception ){
            throw CloudServiceException(e.message)
        }
        return ResponseEntity(CloudServiceUser(userId=user.userId, password = "", serviceUUID = uuid, state = state, authorizationURL = (authenticationService.authorizationURL?.toString() ?: "")),HttpStatus.OK)
    }

}