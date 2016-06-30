package com.irotsoma.cloudbackenc.centralcontroller

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.*


/**
 * Created by justin on 6/19/2016.
 */


@SpringBootApplication
@ImportResource("classpath*:D:/git/CloudBackEnc/CentralController/conf/extensions/**/applicationContext.xml")
open class CentralController

fun main(args: Array<String>) {
    val context = SpringApplication.run(CentralController::class.java, *args)
    //TODO: Check for loaded implementations of CloudServiceFactory to see if GoogleDrive plugin loaded using ImportResource

    var cloudServices = context.getBeansOfType(CloudServiceFactory::class.java)
    val currentBeans = context.beanDefinitionNames
    val test = "test"

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