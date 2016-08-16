package com.irotsoma.cloudbackenc.centralcontroller

import org.springframework.data.repository.CrudRepository

/**
 * Created by irotsoma on 8/15/2016.
 */
interface UserAccountRepository : CrudRepository<UserAccount, Long> {
    fun findByUsername(username: String): UserAccount?
}