package com.irotsoma.cloudbackenc.common

import org.apache.log4j.PropertyConfigurator
import kotlin.reflect.companionObject

/**
 * Created by irotsoma on 7/8/2016.
 * Logging functionality
 * from example: https://stackoverflow.com/questions/34416869/idiomatic-way-of-logging-in-kotlin
 * @code usage:  companion object { val LOG by logger() }
 * @return instance of a log4j logger
 */
fun <R : Any> R.logger(): Lazy<org.apache.log4j.Logger> {
    return lazy { org.apache.log4j.Logger.getLogger(unwrapCompanionClass(this.javaClass).name)}
}

fun <T: Any> unwrapCompanionClass(ofClass: Class<T>): Class<*> {
    return if (ofClass.enclosingClass != null && ofClass.enclosingClass.kotlin.companionObject?.java == ofClass) {
        ofClass.enclosingClass
    } else {
        ofClass
    }
}