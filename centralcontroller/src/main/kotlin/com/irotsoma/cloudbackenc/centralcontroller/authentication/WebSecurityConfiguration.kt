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
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * Created by irotsoma on 8/15/2016.
 *
 * Security configuration for REST controllers
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