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

package com.irotsoma.cloudbackenc.cloudservice

/**
 * Created by irotsoma on 7/13/2016.
 *
 * Object representing a cloud service user for APIs
 * @param userId User ID for the service
 * @param password Password for the service (only for login, should be blank otherwise)
 * @param state Current state of the login process for this user
 * @param authorizationCallbackURL Use only if state = AWAITING_AUTHORIZATION.  A browser with this URL should be opened for the user to authorize the service.
 */
class CloudServiceUser(val userId: String, val password:String, val serviceUUID: String, val state: STATE , val authorizationCallbackURL: String){
    enum class STATE{
        INITIALIZED,
        CANCELLED,
        ERROR,
        LOGGED_IN,
        LOGGED_OUT,
        AWAITING_AUTHORIZATION
    }
}