package com.irotsoma.cloudbackenc.cloudbackencui

import com.irotsoma.cloudbackenc.common.logger
import javafx.scene.control.MenuItem
import javafx.scene.layout.VBox
import javafx.stage.Modality
import javafx.stage.StageStyle
import tornadofx.View


/**
* Created by irotsoma on 7/19/2016.
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
