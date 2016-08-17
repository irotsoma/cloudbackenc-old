package com.irotsoma.cloudbackenc.centralcontroller.controllers

import com.irotsoma.cloudbackenc.centralcontroller.cloudservices.CloudServiceRepository
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceException
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceFactory
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceUser
import com.irotsoma.cloudbackenc.common.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
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
 *
 * Use POST method to /cloudservice/login/{uuid} where {uuid} is the uuid returned from the cloud service list
 * controller for the extension.
 */
@RestController
open class CloudServiceLoginController {
    companion object { val LOG by logger() }
    @Autowired
    private lateinit var cloudServiceRepository: CloudServiceRepository

    @Autowired
    lateinit var messageSource: MessageSource

    @RequestMapping("cloudservice/login/{uuid}", method = arrayOf(RequestMethod.POST))
    fun login(@PathVariable(value="uuid")uuid: UUID, @RequestBody user: CloudServiceUser, @RequestParam(value="locale") locale: Locale) : ResponseEntity<CloudServiceUser> {

        val cloudServiceFactory : Class<CloudServiceFactory> = cloudServiceRepository.cloudServiceExtensions[uuid] ?: throw InvalidPathVariableException("Invalid UUID.")
        val response : CloudServiceUser
        val authenticationService = cloudServiceFactory.newInstance().authenticationService

        try {
            URL(user.authorizationCallbackURL)
        } catch (e: MalformedURLException){ //ignore and let it default to null if URL is invalid or missing
            LOG.debug("Callback URL is invalid.  This probably isn't an issue if a callback is never required for this service.")
        }
        try {
            response = authenticationService.login(user)
        } catch (e:Exception ){
            LOG.debug("Error during login process. ${e.message}")
            throw CloudServiceException(e.message, e)
        }
        //Is this the user object you're looking for?
        if (user.userId != response.userId){
            throw CloudServiceException("User ID returned '${response.userId}' does not match the original user ID '${user.userId}'")
        }
        if (user.serviceUUID != response.serviceUUID){
            throw CloudServiceException("Service UUID returned '${response.serviceUUID}' does not match the original service UUID '${user.serviceUUID}'")
        }
        val status: HttpStatus =
            when(response.state){
                CloudServiceUser.STATE.LOGGED_IN -> HttpStatus.OK
                CloudServiceUser.STATE.AWAITING_AUTHORIZATION -> HttpStatus.PROCESSING
                else -> HttpStatus.BAD_REQUEST
            }


        return ResponseEntity(response, status)
    }

}