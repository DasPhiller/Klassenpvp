package net.fabricmc.example

import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.example.commands.livesCommand
import net.fabricmc.example.commands.playtimeCommand
import net.fabricmc.example.commands.testCommand
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.minecraft.server.BannedPlayerEntry
import net.minecraft.server.network.ServerPlayerEntity
import net.silkmc.silk.commands.registration.setupRegistrationCallback
import net.silkmc.silk.core.text.literalText
import java.io.File
import java.time.DayOfWeek.TUESDAY
import java.time.LocalDate

class KlassenSMP : DedicatedServerModInitializer {

    override fun onInitializeServer() {
        livesCommand.setupRegistrationCallback()
        playtimeCommand.setupRegistrationCallback()
        testCommand.setupRegistrationCallback()
        ServerLifecycleEvents.SERVER_STARTING.register {
            val dayOfWeek = LocalDate.now().dayOfWeek
            if (dayOfWeek == TUESDAY) {
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