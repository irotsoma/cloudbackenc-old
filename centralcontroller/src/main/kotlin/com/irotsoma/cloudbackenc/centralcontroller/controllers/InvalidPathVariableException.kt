package com.irotsoma.cloudbackenc.centralcontroller.controllers

/**
 * Created by irotsoma on 7/13/2016.
 *
 * Custom exception for an invalid value in the uuid field for the controllers that require it.
 */
class InvalidPathVariableException : Exception{
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}