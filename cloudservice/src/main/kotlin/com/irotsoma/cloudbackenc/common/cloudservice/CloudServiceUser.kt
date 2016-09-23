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
/*
 * Created by irotsoma on 7/13/2016.
 */
package com.irotsoma.cloudbackenc.common.cloudservice

/**
 * Object representing a cloud service user for APIs
 *
 */
class CloudServiceUser(
        /**
         * User ID for the service
         */
        val userId: String,
        /**
         * Password for the service (only for login, should be blank otherwise)
         */
        val password:String,
        /**
         * UUID for the cloud service extension.  Defined by the extension author as part of a cloud-service-extension.json file under resources.
         */
        val serviceUUID: String,
        /**
         * Current state of the authorization process for this user
         */
        val state: STATE = CloudServiceUser.STATE.INITIALIZED,
        /**
         * Use only if state = AWAITING_AUTHORIZATION.  A browser with this URL should be opened for the user to authorize the service.
         */
        val authorizationCallbackURL: String?){
    /**
     * Enum describing the various states of user authorization.
     */
    enum class STATE{
        /**
         * Default state when first creating the user object.
         */
        INITIALIZED,
        /**
         * Used when the system is waiting for the user to authorize CloudBackEnc to access their account.  Usually used
         * allong with [CloudServiceUser.authorizationCallbackURL]
         */
        AWAITING_AUTHORIZATION,
        /**
         * Used to indicate the user is logged in to the cloud service and the application is authorized to access their
         * account.
         */
        LOGGED_IN,
        /**
         * Used to indicate that a user has logged out of their account.  Only used if user was previously logged in or
         * when notifying the calling service that a log out operation was successful.  For initial state use
         * [INITIALIZED] instead.
         */
        LOGGED_OUT,
        /**
         * Used to indicate that the user cancelled the login and/or authorization process.
         */
        CANCELLED,
        /**
         * Used to indicate an error ocured when attempting to log into the cloud service.
         */
        ERROR
    }
}