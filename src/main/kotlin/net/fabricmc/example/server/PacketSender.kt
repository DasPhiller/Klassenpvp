package net.fabricmc.example.server

import io.netty.buffer.Unpooled
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.fabricmc.klassenpvp.server.LivesPayload
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier
import net.silkmc.silk.core.Silk
import net.silkmc.silk.core.task.mcCoroutineTask
import kotlin.time.Duration.Companion.seconds

class PacketSender {
    val lives_packet = Identifier.of("modid", "lives")

    fun send(player: ServerPlayerEntity, lives: Int) {
        mcCoroutineTask(sync = false, delay = 2.seconds) {
        val payload = LivesPayload(lives)
        val buf = PacketByteBuf(Unpooled.buffer())

        ServerPlayNetworking.send(player, payload)
        }
    }
}