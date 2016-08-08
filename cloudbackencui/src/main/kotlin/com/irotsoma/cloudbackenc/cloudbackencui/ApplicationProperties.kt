package com.irotsoma.cloudbackenc.cloudbackencui

import java.util.*

/**
 * Created by justin on 7/29/2016.
 *
 * Global properties object for the application.properties file
 */
val applicationProperties = Properties().apply {
     load (ClassLoader.getSystemClassLoader().getResourceAsStream("application.properties"))
}