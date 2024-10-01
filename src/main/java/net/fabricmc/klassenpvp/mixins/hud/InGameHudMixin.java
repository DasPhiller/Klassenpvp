package net.fabricmc.klassenpvp.mixins.hud;

import net.fabricmc.example.client.hud.HudRender;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(method = "renderStatusEffectOverlay", at = @At("HEAD"))
    private void renderStatusEffectOverlay(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        context.getMatrices().push();
        HudRender.INSTANCE.render(context);
        context.getMatrices().pop();
    }
}
