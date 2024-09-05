package net.fabricmc.example.lives

import java.io.File
import java.util.Properties
import java.util.UUID

fun saveConfig(key: UUID, value: Int) {
    val properties = Properties()
    val configFile = File("config.properties")

    if (configFile.exists()) {
        properties.load(configFile.inputStream())
    }

    properties.setProperty(key.toString(), value.toString())
    properties.store(configFile.outputStream(), null)
}


fun getConfigValue(key: UUID): Int? {
    val properties = Properties()
    val configFile = File("config.properties")

    return if (configFile.exists()) {
        properties.load(configFile.inputStream())
        properties.getProperty(key.toString())?.toInt()
    } else {
        null
    }
}

