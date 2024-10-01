package net.fabricmc.klassenpvp.server;

import net.fabricmc.example.server.PacketSender;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record LivesPayload(int lives) implements CustomPayload {
    public static final CustomPayload.Id<LivesPayload> ID = new CustomPayload.Id<>(new PacketSender().getLives_packet());

    public static final PacketCodec<RegistryByteBuf, LivesPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER,
            LivesPayload::lives,
            LivesPayload::new
    );

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
