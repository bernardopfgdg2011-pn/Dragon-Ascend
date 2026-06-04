package com.bernardo.dragonascend.ki;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.List;

public final class KiPlayerManager {

    private static final HashMap<String, KiComponent> kiMap = new HashMap<>();

    private KiPlayerManager() {
    }

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register((MinecraftServer server) -> {
            List<ServerPlayer> players = server.getPlayerList().getPlayers();
            for (int i = 0; i < players.size(); i++) {
                ServerPlayer player = players.get(i);
                String uuid = player.getStringUUID();
                KiComponent ki = kiMap.get(uuid);
                if (ki == null) {
                    ki = new KiComponent();
                    kiMap.put(uuid, ki);
                }
                ki.tick(player.experienceLevel);
            }
        });
    }

    public static KiComponent getKi(String uuid) {
        KiComponent ki = kiMap.get(uuid);
        if (ki == null) {
            ki = new KiComponent();
            kiMap.put(uuid, ki);
        }
        return ki;
    }

    public static void removeKi(String uuid) {
        kiMap.remove(uuid);
    }
}
