package com.irotsoma.cloudbackenc.centralcontroller

import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Created by irotsoma on 8/4/2016.
 *
 * Run before making a call to a server with a self signed certificate.  Should only be used for testing.
 * From:  http://stackoverflow.com/a/7447273/1583160
 */

fun trustSelfSignedSSL() {
    try {
        val ctx = SSLContext.getInstance("TLS")
        val tm = object : X509TrustManager {
            override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
            }
            override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
            }
            override fun getAcceptedIssuers(): Array<out X509Certificate>? {
                return null
            }
        }
        ctx.init(null, arrayOf<TrustManager>(tm), null)
        SSLContext.setDefault(ctx)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

}