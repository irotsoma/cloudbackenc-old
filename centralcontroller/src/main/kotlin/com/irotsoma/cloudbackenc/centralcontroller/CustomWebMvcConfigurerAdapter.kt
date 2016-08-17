package com.irotsoma.cloudbackenc.centralcontroller

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*

/**
 * Created by irotsoma on 8/17/2016.
 *
 * Configuration to allow for the application to use a session based locale resolver
 */

@Configuration
open class CustomWebMvcConfigurerAdapter : WebMvcConfigurerAdapter() {

    //internationalization beans
    @Bean
    open fun localeResolver(): LocaleResolver {
        val slr = SessionLocaleResolver()
        slr.setDefaultLocale(Locale.US)
        return slr
    }

    @Bean
    open fun localeChangeInterceptor(): LocaleChangeInterceptor {
        val localeChangeInterceptor = LocaleChangeInterceptor()
        localeChangeInterceptor.paramName = "locale"
        return localeChangeInterceptor
    }

    override fun addInterceptors(registry: InterceptorRegistry?) {
        registry?.addInterceptor(localeChangeInterceptor())
        super.addInterceptors(registry)
    }
}
