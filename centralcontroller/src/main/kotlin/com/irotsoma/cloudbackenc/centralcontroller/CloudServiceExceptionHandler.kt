package com.irotsoma.cloudbackenc.centralcontroller

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.servlet.http.HttpServletResponse

/**
 * Created by irotsoma on 7/13/2016.
 */
@ControllerAdvice
open class CloudServiceExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(InvalidPathVariableException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun handleInvalidUUID(response: HttpServletResponse, exception:Exception) : String?{
        response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value(), exception.message)
        return exception.message
    }

    @ExceptionHandler(CloudServiceException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleCloudServiceException(response: HttpServletResponse, exception:Exception) : String?{
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.message)
        return exception.message
    }
}