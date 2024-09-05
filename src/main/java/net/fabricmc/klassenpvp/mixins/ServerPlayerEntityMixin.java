package net.fabricmc.klassenpvp.mixins;

import net.fabricmc.example.KlassenSMPKt;
import net.fabricmc.example.lives.PlayerLivesKt;
import net.minecraft.server.network.ServerPlayerEntity;
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
                KlassenSMPKt.banPlayer(player);
            }
            PlayerLivesKt.saveConfig(uuid, lives - 1);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }
}
