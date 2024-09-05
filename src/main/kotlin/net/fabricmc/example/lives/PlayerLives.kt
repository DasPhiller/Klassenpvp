package net.fabricmc.example.lives

import java.io.File
import java.util.Properties

fun saveConfig(key: String, value: Int) {
    val properties = Properties()
    val configFile = File("config.properties")

    if (configFile.exists()) {
        properties.load(configFile.inputStream())
    }

    properties.setProperty(key, value.toString())
    properties.store(configFile.outputStream(), null)
}


fun getConfigValue(key: String): Int? {
    val properties = Properties()
    val configFile = File("config.properties")

    return if (configFile.exists()) {
        properties.load(configFile.inputStream())
        properties.getProperty(key)?.toInt()
    } else {
        null
    }
}

