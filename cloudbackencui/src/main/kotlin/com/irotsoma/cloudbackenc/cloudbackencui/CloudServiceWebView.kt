package com.irotsoma.cloudbackenc.cloudbackencui

import javafx.scene.layout.VBox
import javafx.scene.web.WebView
import tornadofx.View

/**
 * Created by irotsoma on 8/12/16.
 */
class CloudServiceWebView(url: String) : View() {
    override val root: VBox by fxml()
    val cloudServiceWebView: WebView by fxid("cloudServiceWebView")
    init {
        cloudServiceWebView.engine.load(url)
    }
}