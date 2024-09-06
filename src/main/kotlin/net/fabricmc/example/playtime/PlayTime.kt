package net.fabricmc.example.playtime

import net.minecraft.server.network.ServerPlayerEntity
import java.io.File
import java.util.*

fun savePlayConfig(key: UUID, value: Int) {
    val properties = Properties()
    val configFile = File("playtime.properties")

    if (configFile.exists()) {
        properties.load(configFile.inputStream())
    }

    properties.setProperty(key.toString(), value.toString())
    properties.store(configFile.outputStream(), null)
}


fun getPlayValue(key: UUID): Int? {
    val properties = Properties()
    val configFile = File("playtime.properties")

    return if (configFile.exists()) {
        properties.load(configFile.inputStream())
        properties.getProperty(key.toString()).toInt()
    } else {
        null
    }
}

fun playtime(player: ServerPlayerEntity) {

}
