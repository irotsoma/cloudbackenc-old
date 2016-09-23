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

import com.irotsoma.cloudbackenc.common.logger
import javafx.scene.control.MenuItem
import javafx.scene.layout.VBox
import javafx.stage.Modality
import javafx.stage.StageStyle
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import tornadofx.View


/**
* Created by irotsoma on 7/19/2016.
 *
 * Startup view
 * @Lazy to allow for tests to run on the Spring services
*/
@Lazy
@Component
open class MainView : View() {
    companion object { val LOG by logger() }
    override val root: VBox by fxml()
    val menuFileSetupCloudService : MenuItem by fxid("menuFileSetupCloudService")
    init{
        menuFileSetupCloudService.setOnAction{
            CloudServicesUI().openModal(StageStyle.DECORATED,Modality.APPLICATION_MODAL,false)
        }
    }

}