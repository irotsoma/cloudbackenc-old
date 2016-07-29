package com.irotsoma.cloudbackenc.cloudbackencui

import javafx.application.Application
import java.io.File
import java.io.Reader
import java.util.*

/**
 * Created by justin on 7/29/2016.
 */
val applicationProperties = Properties().apply {
     load (ClassLoader.getSystemClassLoader().getResourceAsStream("application.properties"))
}