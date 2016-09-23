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

package com.irotsoma.cloudbackenc.centralcontroller.authentication

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

/**
 * Created by irotsoma on 8/15/2016.
 *
 * User Account Details Service with Autowired Repository
 */
@Component
open class UserAccountDetailsManager : UserDetailsService {
    private lateinit var repository: UserAccountRepository

    @PersistenceContext
    private var test : EntityManager? = null




    @Autowired
    constructor(repository: UserAccountRepository){
        this.repository = repository
    }
    override fun loadUserByUsername(username: String): UserDetails {
        val userAccount = this.repository.findByUsername(username) ?: throw UsernameNotFoundException(" '$username'")
        return User(userAccount.username, userAccount.password, userAccount.roles?.let {AuthorityUtils.createAuthorityList(*getRoles(it))})
    }
    fun getRoles(roles: Collection<Role>) : Array<String>{
        val roleNames :Array<String> = arrayOf()
        for (role in roles){
            role.name?.let{roleNames.plus(it)}
        }
        return roleNames
    }
}