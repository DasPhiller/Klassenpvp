package net.fabricmc.example.server.uti

import kotlinx.coroutines.cancel
import net.fabricmc.example.server.lives.getConfigValue
import net.fabricmc.example.server.playtime.getPlayValue
import net.minecraft.server.network.ServerPlayerEntity
import net.silkmc.silk.core.task.infiniteMcCoroutineTask
import net.silkmc.silk.core.text.literalText
import kotlin.time.Duration.Companion.seconds

fun sendActionBar(player: ServerPlayerEntity) {
    infiniteMcCoroutineTask(true, period = 1.seconds) {
        val ticks = getPlayValue(player.uuid)?.div(20)?.seconds ?: return@infiniteMcCoroutineTask
        player.sendMessage(literalText {
            text("${getConfigValue(player.uuid)} ♥") {
                color = 0xFF5555
            }
            text(" | ") {
                color = 0xAAAAAA
                bold = false
            }
            text("Zeit: $ticks") {
                color = 0x5555FF
            }
        }, true
        )
        if (player.isDisconnected) {
            cancel()
        }
    }
}