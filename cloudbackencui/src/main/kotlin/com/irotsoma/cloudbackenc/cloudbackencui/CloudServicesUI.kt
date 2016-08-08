package com.irotsoma.cloudbackenc.cloudbackencui

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceException
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceExtension
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceExtensionList
import com.irotsoma.cloudbackenc.common.logger
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TableView
import javafx.scene.layout.VBox
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestTemplate
import tornadofx.*

/**
* Created by irotsoma on 7/28/2016.
 *
 * Cloud Services UI functionality
*/
class CloudServicesUI() : Fragment() {
    companion object { val LOG by logger() }
    override val root: VBox by fxml()
    val availableCloudServicesModel: CloudServiceModel = CloudServiceModel(CloudServiceExtension())
    val cloudServicesSetupButton : Button by fxid("cloudServicesSetupButton")
    val cloudServicesRefreshButton : Button by fxid("cloudServiceRefreshButton")
    val cloudServicesRemoveButton : Button by fxid("cloudServicesRemoveButton")
    val availableCloudServicesTable : TableView<CloudServiceExtension> by fxid("availableCloudServicesTable")
    val activeCloudServicesTable : TableView<CloudServiceExtension> by fxid("activeCloudServicesTable")

    init {
        title = "CloudBackEnc"
        val restTemplate = RestTemplate()
        //populate available cloud services list
        with (availableCloudServicesTable) {
            placeholder = Label("Loading Services from Central Controller")
            //asynchronously access the central controller and get a list of available cloud service extensions
            asyncItems {
                try {
                    //determine if ssl is enabled in settings
                    val protocol = if (applicationProperties["centralcontroller.useSSL"] == "true") "https" else "http"
                    //for testing use a hostname verifier that doesn't do any verification
                    if (applicationProperties["centralcontroller.disableCertificateValidation"] == "true"){
                        trustSelfSignedSSL()
                    }
                    restTemplate.getForObject("$protocol://${applicationProperties["centralcontroller.host"]}:${applicationProperties["centralcontroller.port"]}/cloudservices", CloudServiceExtensionList::class.java).observable()
                }
                catch (e: ResourceAccessException){
                    throwError("Error retrieving list of cloud service providers.  Make sure the Central Controller service is running.", e)
                    CloudServiceExtensionList().observable() //this isn't used but is necessary to satisfy the compiler
                }
            }

            //uuid column
            with(column("ID", CloudServiceExtension::uuid)){
                prefWidth=230.0
            }
            //name column
            with(column("Name", CloudServiceExtension::name)){
                prefWidth=270.0
            }
            //bind to list of services through model
            availableCloudServicesModel.rebindOnChange(this){ selectedService -> source = selectedService ?: CloudServiceExtension() }
            //only enable setup button if something is selected
            selectionModel.selectedItemProperty().onChange{
                cloudServicesSetupButton.isDisable = it == null
            }
        }
    }
    //moved the actual throw to a separate function to appease the compiler (unreachable code error).  This allows for adding a custom message to the throw.
    fun throwError(message: String, e: Exception){
        throw(CloudServiceException(message, e))
    }


}