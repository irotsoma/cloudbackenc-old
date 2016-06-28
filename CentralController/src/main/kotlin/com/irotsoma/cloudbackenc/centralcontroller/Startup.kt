package com.irotsoma.cloudbackenc.centralcontroller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.AnnotationConfigUtils
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.ImportResource

/**
 * Created by justin on 6/19/2016.
 */


@SpringBootApplication
@ImportResource("classpath*:config/extensions/**/applicationContext.xml")
open class CentralController

fun main(args: Array<String>) {
    val context = SpringApplication.run(CentralController::class.java, *args)

    //TODO: Check for loaded implementations of CloudServiceFactory to see if GoogleDrive plugin loaded using ImportResource
    /*
    val cloudServiceLoader = CloudServiceLoader()
    cloudServiceLoader.setApplicationContext(context)
    cloudServiceLoader.loadDynamicServices()
    */
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