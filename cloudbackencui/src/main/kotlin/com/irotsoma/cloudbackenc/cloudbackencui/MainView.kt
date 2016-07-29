package com.irotsoma.cloudbackenc.cloudbackencui

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceExtension
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceExtensionList
import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import org.springframework.web.client.RestTemplate
import tornadofx.*


/**
 * Created by justin on 7/19/2016.
 */
class MainView : View() {
    override val root: VBox by fxml()
    val cloudServiceModel : CloudServiceModel = CloudServiceModel(CloudServiceExtension())
    val cloudServicesListView : Pane by fxid("cloudServicesListPane")
    val testSelected : Label by fxid("testSelected")

    init {
        title="CloudBackEnc"
        val restTemplate = RestTemplate()
        val cloudServices = restTemplate.getForObject("http://localhost:9999/cloudservices", CloudServiceExtensionList::class.java).observable()

        with(cloudServicesListView){

            tableview(cloudServices){
                column("ID", CloudServiceExtension::uuid)
                column("Name", CloudServiceExtension::name)
                cloudServiceModel.rebindOnChange(this){selectedService -> source = selectedService ?: CloudServiceExtension()}

            }
        }
        testSelected.bind(cloudServiceModel.name)
    }

}
