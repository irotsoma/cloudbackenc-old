package com.irotsoma.cloudbackenc.centralcontroller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

/**
 * Created by irotsoma on 8/15/2016.
 */
@Component
open class UserAccountDetailsService : UserDetailsService {
    private lateinit var repository: UserAccountRepository

    @Autowired
    constructor(repository: UserAccountRepository){
        this.repository = repository
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val userAccount = this.repository.findByUsername(username) ?: throw UsernameNotFoundException("Username '$username' does not exist.")
        return User(userAccount.username, userAccount.password, AuthorityUtils.createAuthorityList(userAccount.role))
    }
}