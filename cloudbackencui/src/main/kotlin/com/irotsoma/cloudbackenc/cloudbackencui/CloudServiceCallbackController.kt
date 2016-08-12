package com.irotsoma.cloudbackenc.cloudbackencui

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceCallbackURL
import com.irotsoma.cloudbackenc.common.logger
import javafx.application.Platform
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.awt.Desktop
import java.net.URI

/**
* Created by irotsoma on 8/12/16.
*/
@RestController
open class CloudServiceCallbackController {
    companion object { val LOG by logger() }
    @RequestMapping("cloudservicecallback", method = arrayOf(RequestMethod.POST))
    fun authenticate(@RequestBody url: CloudServiceCallbackURL) : ResponseEntity<Void> {
        //try to open the browser to the authentication URL using awt Desktop.  If not possible, use custom webview instead
        Platform.runLater({
            var browserSuccess = false
            try {
                if (Desktop.isDesktopSupported()) {
                    val desktop = Desktop.getDesktop()
                    if (desktop.isSupported(Desktop.Action.BROWSE)) {
                        desktop.browse(URI(url.authorizationURL))
                        browserSuccess = true
                    }
                }
            } catch (e: Exception) {
                LOG.debug("Error getting desktop to display authorization URL to user.  Will try custom view")
            }
            if (!browserSuccess) {
                CloudServiceWebView(url.authorizationURL).openModal()
            }

        })
        return(ResponseEntity(HttpStatus.OK))
    }
}