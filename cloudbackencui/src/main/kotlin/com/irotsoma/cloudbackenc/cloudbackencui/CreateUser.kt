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
/*
 * Created by irotsoma on 10/4/2016.
 */
package com.irotsoma.cloudbackenc.cloudbackencui

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import tornadofx.Fragment
import tornadofx.get

class CreateUser : Fragment() {
    override val root: VBox by fxml()

    val cloudServiceCreateUserIDField : TextField by fxid("cloudServiceCreateUserIDField")
    val cloudServiceCreateUserPasswordField: PasswordField by fxid("cloudServiceCreateUserPasswordField")
    val cloudServiceCreateUserConfirmPasswordField : PasswordField by fxid("cloudServiceCreateUserConfirmPasswordField")
    val cloudServiceCreateUserOkButton : Button by fxid("cloudServiceCreateUserOkButton")
    val cloudServiceCreateUserCancelButton : Button by fxid("cloudServiceCreateUserCancelButton")
    val cloudServiceCreateUserErrorLabel : Label by fxid("cloudServiceCreateUserErrorLabel")

    init {
        title = messages["cloudbackencui.title.create.user"]
        with (cloudServiceCreateUserCancelButton){
            setOnAction{
                closeModal()
            }
        }
        with (cloudServiceCreateUserOkButton){
            setOnAction{
                cloudServiceCreateUserErrorLabel.text = ""
                cloudServiceCreateUserConfirmPasswordField.styleClass.removeAll("error")
                if (cloudServiceCreateUserPasswordField.text != cloudServiceCreateUserConfirmPasswordField.text) {
                    cloudServiceCreateUserErrorLabel.text = messages["cloudbackencui.message.password.mismatch.error"]
                    with(cloudServiceCreateUserConfirmPasswordField) {
                        if (!styleClass.contains("error")) {
                            styleClass.add("error")
                        }
                    }
                    it.consume()
                }
            }
        }
    }
}
