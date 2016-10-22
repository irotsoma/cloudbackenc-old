/*
 * Created by irotsoma on 10/20/16.
 */
package com.irotsoma.cloudbackenc.common


open class RestException(val type : RestExceptionExceptions): Exception() {
    companion object {
        private const val serialVersionUID: Long = 23452365685674564
    }
}