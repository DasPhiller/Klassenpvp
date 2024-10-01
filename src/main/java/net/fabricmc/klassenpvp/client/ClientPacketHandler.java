package net.fabricmc.klassenpvp.client;

import net.fabricmc.example.client.hud.HudRender;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.klassenpvp.server.LivesPayload;

public class ClientPacketHandler {
    public static void registerPacketHandler() {
        PayloadTypeRegistry.playS2C().register(LivesPayload.ID, LivesPayload.CODEC);
        ClientPlayNetworking.registerGlobalReceiver(LivesPayload.ID, (payload, context) -> {

            context.client().execute(() -> {
                System.out.println("Testx2");
            });
        });
    }
}
