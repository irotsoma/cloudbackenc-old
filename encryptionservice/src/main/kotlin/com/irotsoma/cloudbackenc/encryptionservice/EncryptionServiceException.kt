package com.irotsoma.cloudbackenc.encryptionservice

/**
 * Created by irotsoma on 8/18/2016.
 */
class EncryptionServiceException :Exception {
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable) : super(message, cause)
    constructor(cause: Throwable) : super(cause)
}