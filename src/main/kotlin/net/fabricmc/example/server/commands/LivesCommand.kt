package net.fabricmc.example.server.commands

import net.fabricmc.example.server.lives.getConfigValue
import net.fabricmc.example.server.lives.saveConfig
import net.silkmc.silk.commands.command
import net.silkmc.silk.core.Silk
import net.silkmc.silk.core.text.literalText
import net.silkmc.silk.core.text.sendText

val livesCommand = command("lives") {
    requiresPermissionLevel(4)
    runs {
        val uuid = source.player?.uuid ?: return@runs
        source.player?.sendText(literalText("Deine Lebenspunkte sind ${getConfigValue(uuid)}"))
    }
    argument<Int>("amount") { amount ->
        argument<String>("player") { target ->
            runs {
                val server = source.player?.server ?: return@runs
                val targetPlayer = server.playerManager.getPlayer(target()) ?: return@runs
                val uuid = targetPlayer.uuid
                saveConfig(uuid, amount())
                source.player?.sendText(literalText("Du hast den Lebenspunkten von ${target()} auf ${amount()} gesetzt!"))
            }
           /*
            val playerList = Silk.currentServer?.playerManager?.playerList ?: return@command
            val list = ArrayList<String>()
            playerList.forEach {
                list.add(it.name.toString())
            }
            suggestListSuspending { list }


            */
        }
    }
}
