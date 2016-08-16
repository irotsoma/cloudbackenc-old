package com.irotsoma.cloudbackenc.centralcontroller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter

/**
 * Created by irotsoma on 8/15/2016.
 */
@Configuration
open class AuthConfigAdapter : GlobalAuthenticationConfigurerAdapter() {

    @Autowired
    lateinit var userDetailsService: UserAccountDetailsService

    override fun init(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
    }

}