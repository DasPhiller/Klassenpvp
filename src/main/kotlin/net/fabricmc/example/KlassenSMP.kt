package net.fabricmc.example

import net.fabricmc.api.DedicatedServerModInitializer
import net.minecraft.server.BanEntry
import net.minecraft.server.BannedPlayerEntry
import net.minecraft.server.network.ServerPlayerEntity
import net.silkmc.silk.core.text.literalText

class KlassenSMP : DedicatedServerModInitializer {

    override fun onInitializeServer() {

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

        server.playerManager.userBanList.add(BannedPlayerEntry(
            player.gameProfile
        ))
    }
}