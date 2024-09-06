package net.fabricmc.example.lives

import net.minecraft.util.Language.load
import java.io.File
import java.util.Properties
import java.util.UUID

private val configFile = File("config.properties")


fun saveConfig(key: UUID, value: Int) {
    val properties = Properties().apply {
        if (configFile.exists()) {
            configFile.inputStream().use { load(it) }
        }
        setProperty(key.toString(), value.toString())
    }
    configFile.outputStream().use {
        properties.store(it, "Lives")
    }
}


fun getConfigValue(key: UUID): Int? {
    val properties = Properties()

    return if (configFile.exists()) {
        configFile.inputStream().use {
            properties.load(it)
        }
        properties.getProperty(key.toString())?.toInt()
    } else {
        null
    }
}

