package com.irotsoma.cloudbackenc.cloudbackencui

import javafx.scene.control.Hyperlink
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.web.WebView
import tornadofx.View

/**
 * Created by irotsoma on 8/12/16.
 */
class CloudServiceWebView(url: String) : View() {
    override val root: VBox by fxml()
    val cloudServiceWebView: WebView by fxid("cloudServiceWebView")
    val cloudServiceWebViewLabel: Label by fxid("cloudServiceWebViewLabel")
    val cloudServiceWebViewHyperlink: Hyperlink by fxid("cloudServiceWebViewHyperlink")
    init {
        //TODO: label should come from resource bundle
        cloudServiceWebView.engine.load(url)
        cloudServiceWebViewHyperlink.text = url
    }
}