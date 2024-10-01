package net.fabricmc.example.client

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.klassenpvp.client.ClientPacketHandler

class ClientInit : ClientModInitializer {
    override fun onInitializeClient() {
        ClientPacketHandler.registerPacketHandler()
    }
}