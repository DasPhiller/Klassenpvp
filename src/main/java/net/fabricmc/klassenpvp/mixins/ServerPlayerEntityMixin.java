package net.fabricmc.klassenpvp.mixins;

import net.fabricmc.example.server.ServerInitKt;
import net.fabricmc.example.server.PacketSender;
import net.fabricmc.example.server.lives.PlayerLivesKt;
import net.fabricmc.example.server.playtime.PlayTimeKt;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onDeath(CallbackInfo info) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        UUID uuid = player.getUuid();
        try {
            int lives = PlayerLivesKt.getConfigValue(uuid);
            if (lives == 1) {
                ServerInitKt.banPlayer(player);
            }
            PlayerLivesKt.saveConfig(uuid, lives - 1);
            PlayerLivesKt.sendMessage(player);
            new PacketSender().send(player, lives);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(CallbackInfo info) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        UUID uuid = player.getUuid();
        if (PlayTimeKt.getPlayValue(uuid) == 200) {
            player.sendMessage(Text.literal("Du wirst in 10 Sekunden gekickt!"));
        }
        if (PlayTimeKt.getPlayValue(uuid) == 0) {
            player.networkHandler.disconnect(Text.literal("You don't have any time left for the week!"));
        }
        if (PlayTimeKt.getPlayValue(uuid) != 0) {
            PlayTimeKt.setPlaytime(uuid, PlayTimeKt.getPlayValue(uuid) - 1);
        }
    }
}
