package com.irotsoma.cloudbackenc.centralcontroller

/**
 * Created by irotsoma on 7/13/2016.
 */
class InvalidPathVariableException : Exception{
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}