package net.fabricmc.example.client.hud

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import java.awt.Color

object HudRender {

    var lives = 0
    var playtime = 0

    fun render(context: DrawContext) {
        context.matrices.push()
        val color = 0xFFFFFF
        val rgb = Color(color)
        RenderSystem.disableDepthTest()
        context.drawText(minecraft.textRenderer, lives.toString(), 0, 0, rgb.rgb, true)
    }

}
val minecraft: MinecraftClient = MinecraftClient.getInstance()