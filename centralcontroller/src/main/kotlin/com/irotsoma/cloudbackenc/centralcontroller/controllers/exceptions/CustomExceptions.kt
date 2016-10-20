/*
 * Created by irotsoma on 10/20/16.
 */
package com.irotsoma.cloudbackenc.centralcontroller.controllers.exceptions

import com.irotsoma.cloudbackenc.common.RestException
import com.irotsoma.cloudbackenc.common.RestExceptionExceptions

/**
 * Custom exception for trying to create a duplicate user.
 */
class DuplicateUserException() : RestException(RestExceptionExceptions.Duplicate_User)

class InvalidCloudServiceUUID() : RestException(RestExceptionExceptions.Invalid_Cloud_Service_UUID)