package com.irotsoma.cloudbackenc.centralcontroller

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


/**
 * Created by irotsoma on 6/19/2016.
 */


@SpringBootApplication
open class CentralController{

}

fun main(args: Array<String>) {
    System.setProperty("log4j.debug", "")
    val context = SpringApplication.run(CentralController::class.java, *args)
    val cloudServiceLoader = CloudServiceLoader()
    cloudServiceLoader.setApplicationContext(context)
    cloudServiceLoader.loadDynamicServices()

}


/*
fun main(args : Array<String>)  {
var context = AnnotationConfigApplicationContext()
    val cloudServiceLoader = CloudServiceLoader()
    cloudServiceLoader.setApplicationContext(context)
    cloudServiceLoader.loadDynamicServices()



    context.scan("com.irotsoma.cloudbackenc.cloudservice.*")
    context.refresh()


}
*/