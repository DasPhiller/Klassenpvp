package net.fabricmc.example.server.commands

import net.fabricmc.example.server.playtime.setPlaytime
import net.silkmc.silk.commands.command

val playtimeCommand = command("playtime") {
    requiresPermissionLevel(4)
    argument<Int>("seconds") { seconds ->
        argument<String>("player") { target ->
            runs {
                val server = source.player?.server ?: return@runs
                val targetPlayer = server.playerManager.getPlayer(target()) ?: return@runs
                setPlaytime(targetPlayer.uuid, seconds() * 20)
            }
      /*
            val playerList = Silk.currentServer?.playerManager?.playerList ?: return@command
            val list = ArrayList<String>()
            playerList.forEach {
                list.add(it.name.toString())
            }
            suggestList { list }

       */
        }
    }
}