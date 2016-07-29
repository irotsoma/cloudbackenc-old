package com.irotsoma.cloudbackenc.cloudbackencui

import com.irotsoma.cloudbackenc.cloudservice.CloudServiceExtension
import com.irotsoma.cloudbackenc.cloudservice.CloudServiceExtensionList
import com.irotsoma.cloudbackenc.common.logger
import javafx.scene.control.Label
import javafx.scene.control.MenuItem
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.stage.Modality
import javafx.stage.StageStyle
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestTemplate
import tornadofx.*


/**
 * Created by justin on 7/19/2016.
 */
class MainView : View() {
    companion object { val LOG by logger() }
    override val root: VBox by fxml()
    val menuFileSetupCloudService : MenuItem by fxid("menuFileSetupCloudService")
    init{
        menuFileSetupCloudService.setOnAction{
            CloudServicesUI().openModal(StageStyle.DECORATED,Modality.APPLICATION_MODAL,false)
        }
    }

}
