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
 * Created by irotsoma on 8/15/2016.
 */
package com.irotsoma.cloudbackenc.centralcontroller.authentication

import com.fasterxml.jackson.annotation.JsonIgnore
import com.irotsoma.cloudbackenc.common.CloudBackEncRoles
import com.irotsoma.cloudbackenc.common.CloudBackEncUser

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.*

/**
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
    //TODO: Add validation for email formatting
    var email: String? = null
    @JsonIgnore
    var password: String? = null
        set(value) {
            field = PASSWORD_ENCODER.encode(value)
        }
    var enabled: Boolean = true

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = arrayOf(JoinColumn(name = "user_id", referencedColumnName = "id")))
    @Column(name="role")
    var roleList: List<String>? = null
    var roles: List<CloudBackEncRoles>?
        set(value){
            roleList = value?.map{it.name}
        }
        get(){
            return roleList?.map{ CloudBackEncRoles.valueOf(it)}
        }

    constructor(username: String, password: String, email: String?, enabled: Boolean?, roles: List<CloudBackEncRoles>): this() {
        this.username = username
        this.password = password
        this.email = email
        this.roles = roles
        this.enabled = enabled ?: true
    }
    fun cloudBackEncUser(): CloudBackEncUser{
        return CloudBackEncUser(username!!, CloudBackEncUser.PASSWORD_MASKED, email, enabled, roles?: emptyList())
    }
}