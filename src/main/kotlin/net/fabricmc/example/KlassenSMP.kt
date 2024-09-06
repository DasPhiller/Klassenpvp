package net.fabricmc.example

import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.minecraft.server.BannedPlayerEntry
import net.minecraft.server.network.ServerPlayerEntity
import net.silkmc.silk.core.text.literalText
import java.io.File
import java.time.DayOfWeek.FRIDAY
import java.time.LocalDate

class KlassenSMP : DedicatedServerModInitializer {

    override fun onInitializeServer() {
        ServerLifecycleEvents.SERVER_STARTING.register {
            val dayOfWeek = LocalDate.now().dayOfWeek
            if (dayOfWeek == FRIDAY) {
                val file = File("playtime.properties")
                file.delete()
            }
        }
    }
}

fun banPlayer(player: ServerPlayerEntity) {
    val server = player.server
    if (server != null) {

        val banMessage = literalText("Skill Issue") {
            color = 0xff0000
            bold = true
        }
        player.networkHandler.disconnect(banMessage)

        server.playerManager.userBanList.add(
            BannedPlayerEntry(
                player.gameProfile
            )
        )
    }
}