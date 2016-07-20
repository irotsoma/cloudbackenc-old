package com.irotsoma.cloudbackenc.centralcontroller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

/**
 * Created by irotsoma on 6/19/2016.
 */

@SpringBootApplication
open class CentralController{
    @Bean
    @Primary
    open fun objectMapper() = ObjectMapper().apply {
        registerModule(KotlinModule())
    }
}

fun main(args: Array<String>) {
    //val context =
    SpringApplication.run(CentralController::class.java, *args)
}