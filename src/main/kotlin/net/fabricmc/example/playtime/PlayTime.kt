package net.fabricmc.example.playtime

import java.io.File
import java.util.*

val configFile = File("playtime.properties")

fun setPlaytime(key: UUID, value: Int) {
    val properties = Properties().apply {
        if (configFile.exists()) {
            configFile.inputStream().use { load(it) }
        }
        setProperty(key.toString(), value.toString())
    }

    configFile.outputStream().use {
        properties.store(it, "Playtime")
    }
}

fun getPlayValue(key: UUID): Int? {
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