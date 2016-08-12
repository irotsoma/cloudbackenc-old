package com.irotsoma.cloudbackenc.cloudbackencui

import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Lazy
import tornadofx.App
import tornadofx.FX

/**
* Created by irotsoma on 7/19/2016.
 *
 * TornadoFX app class launched by spring boot
 * Based on JavaFx example:  https://github.com/thomasdarimont/spring-labs/tree/master/spring-boot-javafx
 * App param = startup view class, style class
*/

@Lazy
@SpringBootApplication
open class CloudBackEncUIApp : App(MainView::class, CloudBackEncUIStyles::class){
    companion object{
        private var savedArgs: Array<String> = emptyArray()
        fun launchApp(args: Array<String>){
            savedArgs = args
            @Suppress("JAVA_CLASS_ON_COMPANION")
            launch(CloudBackEncUIApp::class.java, *args)
        }
    }
    private var applicationContext: ConfigurableApplicationContext? = null

    @Autowired lateinit var mainView: MainView

    @Value("\${app.ui.title}")
    private var windowTitle: String? = null

    override fun init() {
        applicationContext = SpringApplication.run(arrayOf(CloudBackEncUIApp::class.java), savedArgs)
        applicationContext!!.autowireCapableBeanFactory.autowireBean(this)
    }

    override fun start(stage: Stage) {
        FX.registerApplication(this,stage)
        stage.title = windowTitle
        stage.scene = Scene(mainView.root)
        stage.isResizable = true
        stage.centerOnScreen()
        stage.show()
    }

    override fun stop() {
        super.stop()
        applicationContext!!.close()
    }

}

fun main(args: Array<String>) {
    CloudBackEncUIApp.launchApp(args)

}