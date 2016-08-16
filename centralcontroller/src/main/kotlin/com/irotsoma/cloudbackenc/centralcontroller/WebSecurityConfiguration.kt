package com.irotsoma.cloudbackenc.centralcontroller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * Created by irotsoma on 8/15/2016.
 */

@Configuration
@EnableWebSecurity
open class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var userDetailsService : UserAccountDetailsService

    override fun configure(auth: AuthenticationManagerBuilder){
        auth.userDetailsService(this.userDetailsService).passwordEncoder(UserAccount.PASSWORD_ENCODER)
    }

    override fun configure(http: HttpSecurity){
        http
            .authorizeRequests()
                .antMatchers("/cloudservices").permitAll() //no need to secure the list
                .anyRequest().authenticated()
                .and()
            .httpBasic()
                .and()
            .headers()
                .frameOptions().disable() //needed to get h2 console working
                .and()
            .csrf().disable() //TODO: enable CSRF protection
    }
}