package com.irotsoma.cloudbackenc.centralcontroller

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Created by irotsoma on 6/19/2016.
 */

@SpringBootApplication
open class CentralController  {


    //TODO: Swagger API documentation
}

fun main(args: Array<String>) {
    //val context =
    SpringApplication.run(CentralController::class.java, *args)
}