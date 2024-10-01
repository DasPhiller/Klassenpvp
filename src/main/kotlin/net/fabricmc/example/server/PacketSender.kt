package net.fabricmc.example.server

import io.netty.buffer.Unpooled
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.fabricmc.klassenpvp.server.LivesPayload
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier

class PacketSender {
    val lives_packet = Identifier.of("modid", "lives")

    fun send(player: ServerPlayerEntity, lives: Int) {
        val payload = LivesPayload(lives)
        val buf = PacketByteBuf(Unpooled.buffer())

        ServerPlayNetworking.send(player, payload)
    }
}