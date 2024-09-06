package net.fabricmc.example.uti

import kotlinx.coroutines.cancel
import net.fabricmc.example.lives.getConfigValue
import net.minecraft.server.network.ServerPlayerEntity
import net.silkmc.silk.core.task.infiniteMcCoroutineTask
import net.silkmc.silk.core.text.literalText
import kotlin.time.Duration.Companion.seconds

fun sendActionBar(player: ServerPlayerEntity) {
    infiniteMcCoroutineTask(true, period = 1.seconds) {
        player.sendMessage(literalText("${getConfigValue(player.uuid)} ♥") {
            color = 0xff0000
        }, true)
        if (player.isDisconnected) {
            cancel()
        }
    }
}