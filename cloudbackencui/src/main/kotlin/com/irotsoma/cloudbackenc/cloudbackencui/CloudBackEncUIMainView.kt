package com.irotsoma.cloudbackenc.cloudbackencui

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceExtensionNames
import javafx.beans.property.ListProperty
import javafx.beans.property.MapProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleMapProperty
import javafx.scene.control.ListView
import javafx.scene.layout.BorderPane
import org.springframework.web.client.RestTemplate
import tornadofx.View
import tornadofx.observable
import java.util.*


/**
 * Created by justin on 7/19/2016.
 */
class CloudBackEncUIMainView : View() {
    override val root: BorderPane by fxml()
    val cloudServicesList : MapProperty<UUID,String> = SimpleMapProperty<UUID,String>()
    val cloudServicesListView : ListView<String> by fxid("cloudServicesListView")

    init {
        title="CloudBackEnc"
        val restTemplate = RestTemplate()
        val cloudServices = restTemplate.getForObject("http://localhost:9999/cloudservices", CloudServiceExtensionNames::class.java)

        cloudServicesListView.itemsProperty().bind(cloudServices.values)
    }

}
