package net.fabricmc.example.client.hud

import com.mojang.blaze3d.systems.RenderSystem
import net.fabricmc.example.server.lives.getConfigValue
import net.fabricmc.example.server.playtime.getPlayValue
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.network.ServerPlayerEntity
import java.awt.Color
import kotlin.time.Duration.Companion.seconds

object HudRender {


    fun render(context: DrawContext, player: PlayerEntity) {
        context.matrices.push()
        val color = 0xFFFFFF
        val ticks = getPlayValue(player.uuid)?.div(20)?.seconds ?: return
        val rgb = Color(color)
        RenderSystem.disableDepthTest()
        context.drawText(minecraft.textRenderer, "${getConfigValue(player.uuid)} ♥", 798, 0, 0xFF0000, true)
        context.drawText(minecraft.textRenderer, ticks.toString(), 798, 10, 0x0000FF, true)

    }

}
val minecraft: MinecraftClient = MinecraftClient.getInstance()