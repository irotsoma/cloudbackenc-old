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
import com.irotsoma.cloudbackenc.centralcontroller.authentication.UserAccount
import com.irotsoma.cloudbackenc.centralcontroller.authentication.UserAccountDetailsManager
import com.irotsoma.cloudbackenc.centralcontroller.controllers.exceptions.DuplicateUserException
import com.irotsoma.cloudbackenc.common.CloudBackEncRoles
import com.irotsoma.cloudbackenc.common.CloudBackEncUser
import com.irotsoma.cloudbackenc.common.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod


@Controller
@RequestMapping("/users")
open class UserController {
    companion object { val LOG by logger() }
    @Autowired
    private lateinit var userAccountDetailsManager: UserAccountDetailsManager

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    @Secured("ROLE_ADMIN")
    fun createUser(@RequestBody user: CloudBackEncUser): ResponseEntity<CloudBackEncUser>{
        val auth = SecurityContextHolder.getContext().authentication
        val currentUser = userAccountDetailsManager.loadUserByUsername(auth.name)
        if (!currentUser.authorities.contains(GrantedAuthority{CloudBackEncRoles.ROLE_ADMIN.name}))
        {
            return ResponseEntity(null, HttpStatus.FORBIDDEN)
        }
        //check to see if there is a duplicate user by attempting to load the user and catching the exception for username not found
        var duplicateUser:Boolean = true
        try{
            userAccountDetailsManager.loadUserByUsername(user.userId)
        } catch(e: UsernameNotFoundException){
            duplicateUser = false
        }
        if (duplicateUser){
            throw DuplicateUserException()
        }
        val newUserAccount = UserAccount(user.userId, user.password,user.emailAddress, user.roles.map { CloudBackEncRoles.valueOf(it) })
        userAccountDetailsManager.userRepository.saveAndFlush(newUserAccount)

        return ResponseEntity(user, HttpStatus.OK)
    }

    @RequestMapping(method = arrayOf(RequestMethod.PUT))
    fun updateUser(@RequestBody updatedUser:CloudBackEncUser) : ResponseEntity<CloudBackEncUser>{
        val auth = SecurityContextHolder.getContext().authentication
        if ((updatedUser.userId != auth.name) && (!auth.authorities.contains(GrantedAuthority{"USER_ADMIN"})))
        {
            return ResponseEntity(null, HttpStatus.FORBIDDEN)
        }
        //TODO implement this
        return ResponseEntity(updatedUser, HttpStatus.OK)
    }
    @RequestMapping(method = arrayOf(RequestMethod.DELETE))
    @Secured("ROLE_ADMIN")
    fun deleteUser(@RequestBody updatedUser:CloudBackEncUser) : ResponseEntity<CloudBackEncUser>{
        val authorizedUser = SecurityContextHolder.getContext().authentication.name


        //TODO implement this
        return ResponseEntity(updatedUser, HttpStatus.OK)
    }
}