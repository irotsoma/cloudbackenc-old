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

import com.fasterxml.jackson.annotation.JsonIgnore

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.*

/**
 * Created by irotsoma on 8/15/2016.
 *
 * User Account Object
 */
@Entity
@Table(name = "user_account")
class UserAccount() {
    companion object {
        val PASSWORD_ENCODER: PasswordEncoder = BCryptPasswordEncoder()
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var username: String? = null
    var email: String? = null
    @JsonIgnore
    var password: String? = null
        set(value) {
            field = PASSWORD_ENCODER.encode(value)
        }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = arrayOf(JoinColumn(name = "user_id", referencedColumnName = "id")), inverseJoinColumns = arrayOf(JoinColumn(name = "role_id", referencedColumnName = "id")))
    var roles: Collection<Role>? = null

    constructor(username: String, password: String, email: String?, roles: Collection<Role>) : this() {
        this.username = username
        this.password = password
        this.email = email
        this.roles = roles
    }
}