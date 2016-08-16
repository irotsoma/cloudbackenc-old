package com.irotsoma.cloudbackenc.centralcontroller

import com.fasterxml.jackson.annotation.JsonIgnore

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Created by irotsoma on 8/15/2016.
 */
@Entity
class UserAccount() {
    companion object {
        val PASSWORD_ENCODER: PasswordEncoder = BCryptPasswordEncoder()
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var username: String? = null
    @JsonIgnore
    var password: String? = null
        set(value) {
            field = PASSWORD_ENCODER.encode(value)
        }
    var role: String? = null

    constructor(username: String, password: String, role: String) : this() {
        this.username = username
        this.password = password
        this.role = role
    }

}