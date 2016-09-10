/*
 * Copyright (C) 2016  Irotsoma, LLC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.irotsoma.cloudbackenc.cloudbackencui

import com.irotsoma.cloudbackenc.common.cloudservice.CloudServiceException
import com.irotsoma.cloudbackenc.common.cloudservice.CloudServiceExtension
import com.irotsoma.cloudbackenc.common.cloudservice.CloudServiceExtensionList
import com.irotsoma.cloudbackenc.common.cloudservice.CloudServiceUser
import com.irotsoma.cloudbackenc.common.logger
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TableView
import javafx.scene.layout.VBox
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
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
            availableCloudServicesModel.rebindOnChange(this){ selectedService -> service = selectedService ?: CloudServiceExtension() }
            //only enable setup button if something is selected
            selectionModel.selectedItemProperty().onChange{
                cloudServicesSetupButton.isDisable = it == null
            }
        }

        with (cloudServicesSetupButton){
            setOnAction {
                LOG.debug("Attempting to set up cloud service ${availableCloudServicesModel.service.uuid.toString()}: ${availableCloudServicesModel.service.name}")
                val protocol = if (applicationProperties["centralcontroller.useSSL"] == "true") "https" else "http"
                //for testing use a hostname verifier that doesn't do any verification
                if ((applicationProperties["centralcontroller.useSSL"] == "true") && (applicationProperties["centralcontroller.disableCertificateValidation"] == "true")){
                    trustSelfSignedSSL()
                    LOG.warn("SSL is enabled, but certificate validation is disabled.")
                }
                val requestHeaders = HttpHeaders()
                requestHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)

                //TODO: add real user IDs instead of defaulting to irotsomadev
                //TODO: calculate callback address dynamically

                val httpEntity = HttpEntity<CloudServiceUser>(CloudServiceUser("irotsomadev","",availableCloudServicesModel.service.uuid.toString(), CloudServiceUser.STATE.INITIALIZED,"http://localhost:9998/cloudservicecallback"), requestHeaders)
                LOG.debug("Connecting to central controller cloud service login service at $protocol://${applicationProperties["centralcontroller.host"]}:${applicationProperties["centralcontroller.port"]}/cloudservice/login/${availableCloudServicesModel.service.uuid.toString()}")
                runAsync {
                    val callResponse = restTemplate.postForEntity("$protocol://${applicationProperties["centralcontroller.host"]}:${applicationProperties["centralcontroller.port"]}/cloudservice/login/${availableCloudServicesModel.service.uuid.toString()}", httpEntity, CloudServiceUser::class.java)
                    LOG.debug("Cloud service setup call response: ${callResponse.statusCode}: ${callResponse.statusCodeValue}")
                    LOG.debug("Cloud service user state: ${callResponse.body.state.name}")
                }
            }
        }
    }
    //moved the actual throw to a separate function to appease the compiler (unreachable code error).  This allows for adding a custom message to the throw.
    fun throwError(message: String, e: Exception){
        throw(CloudServiceException(message, e))
    }


}