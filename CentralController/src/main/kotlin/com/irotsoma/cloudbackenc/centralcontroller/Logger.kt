package com.irotsoma.cloudbackenc.centralcontroller

import org.apache.log4j.Logger
import kotlin.reflect.companionObject

/**
 * Created by irotsoma on 7/8/2016.
 * logging functionality
 * from example: https://stackoverflow.com/questions/34416869/idiomatic-way-of-logging-in-kotlin
 * usage:  companion object { val LOG by logger() }
 */
fun <R : Any> R.logger(): Lazy<Logger> {

    return lazy { Logger.getLogger(unwrapCompanionClass(this.javaClass).name)}
}

fun <T: Any> unwrapCompanionClass(ofClass: Class<T>): Class<*> {
    return if (ofClass.enclosingClass != null && ofClass.enclosingClass.kotlin.companionObject?.java == ofClass) {
        ofClass.enclosingClass
    } else {
        ofClass
    }
}