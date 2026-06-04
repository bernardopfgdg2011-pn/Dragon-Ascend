package com.bernardo.dragonascend.race;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;

public final class RacePassives {

    public static final long ZENKAI_COOLDOWN = 720_000L;
    private static final ResourceLocation ZENKAI_ID = ResourceLocation.fromNamespaceAndPath("dragon_ascend", "zenkai_boost");
    private static final HashMap<String, RaceComponent> raceMap = new HashMap<>();

    private RacePassives() {
    }

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register((MinecraftServer server) -> {
            List<ServerPlayer> players = server.getPlayerList().getPlayers();
            for (int i = 0; i < players.size(); i++) {
                ServerPlayer player = players.get(i);
                RaceComponent comp = raceMap.get(player.getStringUUID());
                if (comp == null) {
                    comp = new RaceComponent(Race.SAIYAN);
                    raceMap.put(player.getStringUUID(), comp);
                }
                tick(player, comp);
            }
        });
    }

    public static void setRace(String uuid, Race race) {
        raceMap.put(uuid, new RaceComponent(race));
    }

    public static Race getRace(String uuid) {
        RaceComponent component = raceMap.get(uuid);
        return component != null ? component.race : null;
    }

    private static void tick(ServerPlayer player, RaceComponent component) {
        switch (component.race) {
            case SAIYAN:
                tickSaiyan(player, component);
                break;
            case NAMEKIAN:
                tickNamekian(player);
                break;
        }
    }

    private static void tickSaiyan(ServerPlayer player, RaceComponent component) {
        if (component.zenkaiCooldownTicks > 0) {
            component.zenkaiCooldownTicks--;
            return;
        }
        if (player.getHealth() / player.getMaxHealth() <= 0.2f) {
            component.zenkaiCooldownTicks = ZENKAI_COOLDOWN;
            AttributeInstance healthInstance = player.getAttributes().getInstance(Attributes.MAX_HEALTH);
            if (healthInstance != null) {
                healthInstance.addPermanentModifier(new AttributeModifier(ZENKAI_ID, 2.0, AttributeModifier.Operation.ADD_VALUE));
            }
        }
    }

    private static void tickNamekian(ServerPlayer player) {
        if (player.tickCount % 40 == 0) {
            player.heal(0.5f);
        }
    }
}
