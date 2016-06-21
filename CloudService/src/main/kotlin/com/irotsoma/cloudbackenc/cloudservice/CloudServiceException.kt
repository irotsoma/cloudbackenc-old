package com.irotsoma.cloudbackenc.cloudservice

/**
 * Created by justin on 6/20/2016.
 *
 * Exception class for Cloud Service Implementations
 */
class CloudServiceException : Exception{
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}