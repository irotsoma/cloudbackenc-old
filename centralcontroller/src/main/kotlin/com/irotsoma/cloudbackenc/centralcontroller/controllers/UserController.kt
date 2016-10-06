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
/*
 * Created by irotsoma on 9/22/2016.
 */
import com.irotsoma.cloudbackenc.centralcontroller.authentication.UserAccountDetailsManager
import com.irotsoma.cloudbackenc.common.CloudBackEncUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


@Controller
@RequestMapping("/users")
open class UserController {
    @Autowired
    private lateinit var userAccountDetailsManager: UserAccountDetailsManager

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun createUser(@RequestBody user: CloudBackEncUser): ResponseEntity<CloudBackEncUser>{

        //TODO implement this
        return ResponseEntity(user, HttpStatus.OK)
    }

    @RequestMapping(method = arrayOf(RequestMethod.PUT))
    fun updateUser(@RequestBody updatedUser:CloudBackEncUser) : ResponseEntity<CloudBackEncUser>{
        val auth = SecurityContextHolder.getContext().authentication
        if ((updatedUser.userId != auth.name) && (!auth.authorities.contains(GrantedAuthority{"USER_ADMIN"})))
        {
            return ResponseEntity(null, HttpStatus.FORBIDDEN)
        }
        userAccountDetailsManager.
        //TODO implement this
        return ResponseEntity(updatedUser, HttpStatus.OK)
    }
    @RequestMapping(method = arrayOf(RequestMethod.DELETE))
    fun deleteUser(@RequestBody updatedUser:CloudBackEncUser) : ResponseEntity<CloudBackEncUser>{
        val authorizedUser = SecurityContextHolder.getContext().authentication.name


        //TODO implement this
        return ResponseEntity(updatedUser, HttpStatus.OK)
    }
}