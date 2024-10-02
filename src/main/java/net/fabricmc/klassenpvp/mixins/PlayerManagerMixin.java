package net.fabricmc.klassenpvp.mixins;

import net.fabricmc.example.server.PacketSender;
import net.fabricmc.example.server.lives.PlayerLivesKt;
import net.fabricmc.example.server.playtime.PlayTimeKt;
import net.fabricmc.example.server.uti.UtilsKt;
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
        Integer value = PlayTimeKt.getPlayValue(uuid);
        if (value == null) {
            System.out.println("null time");
            PlayTimeKt.setPlaytime(uuid, 504000);
        }
        System.out.println(uuid);
        //new PacketSender().send(player, PlayerLivesKt.getConfigValue(uuid));
        System.out.println(PlayTimeKt.getPlayValue(uuid));
        if (PlayTimeKt.getPlayValue(uuid) == null) {
            PlayTimeKt.setPlaytime(uuid, 504000);
            System.out.println(PlayTimeKt.getPlayValue(uuid));
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
