package com.irotsoma.cloudbackenc.centralcontroller.authentication

import org.springframework.data.repository.CrudRepository

/**
 * Created by irotsoma on 8/15/2016.
 *
 * Repository object for storing user accounts
 */
interface UserAccountRepository : CrudRepository<UserAccount, Long> {
    fun findByUsername(username: String): UserAccount?
}