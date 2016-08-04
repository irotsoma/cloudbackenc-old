package com.irotsoma.cloudbackenc.cloudbackencui

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceException
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceExtension
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceExtensionList
import com.irotsoma.cloudbackenc.common.logger
import javafx.scene.control.Label
import javafx.scene.control.TableView
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestTemplate
import tornadofx.*

/**
* Created by irotsoma on 7/28/2016.
*/
class CloudServicesUI() : Fragment() {
    companion object { val LOG by logger() }
    override val root: VBox by fxml()
    val cloudServiceModel : CloudServiceModel = CloudServiceModel(CloudServiceExtension())
    val cloudServicesListView : Pane by fxid("cloudServicesListPane")
    val testSelected : Label by fxid("testSelected")
    val cloudServicesTable : TableView<CloudServiceExtension> by fxid("cloudServicesTable")

    init {
        title = "CloudBackEnc"
        val restTemplate = RestTemplate()
        with (cloudServicesTable) {
            placeholder = Label("Loading Services from Central Controller")
            asyncItems {
                try {
                    restTemplate.getForObject("http://localhost:${applicationProperties["centralcontroller.port"]}/cloudservices", CloudServiceExtensionList::class.java).observable()
                }
                catch (e: ResourceAccessException){
                    throwError("Error retrieving list of cloud service providers.  Make sure the Central Controller service is running.")
                    CloudServiceExtensionList().observable()
                }
            }
            column("ID", CloudServiceExtension::uuid)
            column("Name", CloudServiceExtension::name)
            cloudServiceModel.rebindOnChange(this){ selectedService -> source = selectedService ?: CloudServiceExtension() }
        }




    //TODO remove this when done testing
    testSelected.bind(cloudServiceModel.name)




    }
    fun throwError(message: String){
        throw(CloudServiceException(message))
    }


}