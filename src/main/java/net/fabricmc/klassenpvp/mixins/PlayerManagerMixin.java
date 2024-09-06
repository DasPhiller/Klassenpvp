package net.fabricmc.klassenpvp.mixins;

import net.fabricmc.example.lives.PlayerLivesKt;
import net.fabricmc.example.playtime.PlayTimeKt;
import net.fabricmc.example.uti.UtilsKt;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ConnectedClientData;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

    @Inject(method = "onPlayerConnect", at = @At("HEAD"), cancellable = true)
    public void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, ConnectedClientData clientData, CallbackInfo ci) {
        UUID uuid = player.getUuid();
        if (PlayTimeKt.getPlayValue(player.getUuid()) == null) {
            PlayTimeKt.savePlayConfig(player.getUuid(), 200);
        }
        if (PlayTimeKt.getPlayValue(uuid) == 0) {
            connection.disconnect(Text.literal("You don't have any time left for the week!"));
            ci.cancel();
        } else {
            if (PlayerLivesKt.getConfigValue(player.getUuid()) == null) {
                PlayerLivesKt.saveConfig(player.getUuid(), 3);
            }
            UtilsKt.sendActionBar(player);
        }

    }
}
