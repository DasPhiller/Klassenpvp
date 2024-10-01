package net.fabricmc.example.server.lives

import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Language.load
import net.silkmc.silk.core.text.literalText
import net.silkmc.silk.core.text.sendText
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

fun sendMessage(player: ServerPlayerEntity) {
    player.sendText(literalText("-1 ♥") {
        color = 0xAA0000
        bold = false
    })
    player.playSoundToPlayer(SoundEvent.of(SoundEvents.BLOCK_NOTE_BLOCK_DIDGERIDOO.value().id), SoundCategory.MASTER, 100f, 1f)
}
