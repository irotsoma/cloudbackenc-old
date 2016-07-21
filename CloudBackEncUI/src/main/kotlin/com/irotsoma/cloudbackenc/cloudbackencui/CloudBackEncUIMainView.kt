package com.irotsoma.cloudbackenc.cloudbackencui

import javafx.beans.property.ListProperty
import javafx.beans.property.SimpleListProperty
import javafx.scene.control.ListView
import javafx.scene.layout.BorderPane
import org.springframework.web.client.RestTemplate
import tornadofx.View


/**
 * Created by justin on 7/19/2016.
 */
class CloudBackEncUIMainView : View() {
    override val root: BorderPane by fxml()
    val cloudServicesList : ListProperty<String> = SimpleListProperty<String>()
    val cloudServicesListView : ListView<String> by fxid("cloudServicesListView")

    init {
        title="CloudBackEnc"
        val restTemplate = RestTemplate()
        val cloudServices = restTemplate.getForObject("http://localhost:9999/cloudservices", List::class.java)
        cloudServicesListView.itemsProperty().bind(cloudServicesList)
    }

}
