package net.fabricmc.klassenpvp.mixins;

import net.fabricmc.example.lives.PlayerLivesKt;
import net.fabricmc.example.uti.UtilsKt;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ConnectedClientData;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

    @Inject(method = "onPlayerConnect", at = @At("HEAD"))
    public void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, ConnectedClientData clientData, CallbackInfo ci) {
        if (PlayerLivesKt.getConfigValue(player.getUuid()) == null) {
            PlayerLivesKt.saveConfig(player.getUuid(), 3);
        }
        UtilsKt.sendActionBar(player);
    }
}
