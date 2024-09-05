package net.fabricmc.klassenpvp.mixins;

import net.fabricmc.example.KlassenSMPKt;
import net.fabricmc.example.lives.PlayerLivesKt;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onDeath(CallbackInfo info) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        String name = player.getName().toString();
        try {
            int lives = PlayerLivesKt.getConfigValue(name);
            if (lives == 0) {
                KlassenSMPKt.banPlayer(player);
            }
            PlayerLivesKt.saveConfig(player.getName().toString(), lives - 1);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }
}
