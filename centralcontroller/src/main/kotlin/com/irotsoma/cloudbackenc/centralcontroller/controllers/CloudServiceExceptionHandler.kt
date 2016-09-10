/*
 * Copyright (C) 2016  Irotsoma, LLC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.irotsoma.cloudbackenc.centralcontroller.controllers

import com.irotsoma.cloudbackenc.common.cloudservice.CloudServiceException
import org.springframework.beans.TypeMismatchException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*
import javax.servlet.http.HttpServletResponse

/**
 * Created by irotsoma on 7/13/2016.
 *
 * Controller advice for custom exceptions.  Allows for customizing the messages returned to the REST client.
 */
@ControllerAdvice
open class CloudServiceExceptionHandler : ResponseEntityExceptionHandler() {

    @Autowired
    lateinit var messageSource: MessageSource


    @ExceptionHandler(CloudServiceException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleCloudServiceException(response: HttpServletResponse, exception:Exception) : String?{
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.message)
        return exception.message
    }

    @ExceptionHandler(InvalidPathVariableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleInvalidPathVariableException(response: HttpServletResponse, exception:Exception) : String?{
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.message)
        return exception.message
    }

    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException?, headers: HttpHeaders?, status: HttpStatus?, request: WebRequest?): ResponseEntity<Any> {
        val responseHeaders = headers ?: HttpHeaders()
        responseHeaders.add(HttpHeaders.WARNING, messageSource.getMessage("centralcontroller.cloudservices.request.format.invalid", null, LocaleContextHolder.getLocale()))
        return super.handleHttpMessageNotReadable(ex, responseHeaders, status, request)
    }

    override fun handleTypeMismatch(ex: TypeMismatchException?, headers: HttpHeaders?, status: HttpStatus?, request: WebRequest?): ResponseEntity<Any> {
        val responseHeaders = headers ?: HttpHeaders()
        if (ex?.requiredType == UUID::class.java){
            responseHeaders.add(HttpHeaders.WARNING, messageSource.getMessage("centralcontroller.cloudservices.uuid.invalid.format", null, LocaleContextHolder.getLocale()))
        }
        return super.handleTypeMismatch(ex, responseHeaders, status, request)
    }
}