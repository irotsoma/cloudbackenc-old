package com.irotsoma.cloudbackenc.centralcontroller

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*

/**
 * Created by irotsoma on 6/19/2016.
 */

@SpringBootApplication
open class CentralController{

    //internationalization beans
    @Bean
    open fun localeResolver(): LocaleResolver {
        val slr = SessionLocaleResolver()
        slr.setDefaultLocale(Locale.US)
        return slr
    }
    @Bean
    open fun messageSource(): ResourceBundleMessageSource {
        val source = ResourceBundleMessageSource()
        source.setBasenames("i18n/messages")
        source.setUseCodeAsDefaultMessage(true)
        source.setDefaultEncoding("UTF-8")
        return source
    }
    //TODO: Swagger API documentation
}

fun main(args: Array<String>) {
    //val context =
    SpringApplication.run(CentralController::class.java, *args)
}