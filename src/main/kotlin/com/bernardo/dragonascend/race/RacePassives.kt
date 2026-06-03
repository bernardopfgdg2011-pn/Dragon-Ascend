package com.bernardo.dragonascend.race

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.resources.ResourceLocation

object RacePassives {

    const val ZENKAI_COOLDOWN = 720_000L
    private val ZENKAI_ID = ResourceLocation.fromNamespaceAndPath("dragon_ascend", "zenkai_boost")
    private val raceMap = HashMap<String, RaceComponent>()

    fun register() {
        ServerTickEvents.END_SERVER_TICK.register { server: MinecraftServer ->
            val players = server.playerList.players
            for (i in 0 until players.size) {
                val player = players[i]
                val comp = raceMap.getOrPut(player.stringUUID) { RaceComponent(Race.SAIYAN) }
                tick(player, comp)
            }
        }
    }

    fun setRace(uuid: String, race: Race) { raceMap[uuid] = RaceComponent(race) }
    fun getRace(uuid: String) = raceMap[uuid]?.race

    private fun tick(player: ServerPlayer, component: RaceComponent) {
        when (component.race) {
            Race.SAIYAN -> tickSaiyan(player, component)
            Race.NAMEKIAN -> tickNamekian(player)
        }
    }

    private fun tickSaiyan(player: ServerPlayer, component: RaceComponent) {
        if (component.zenkaiCooldownTicks > 0) { component.zenkaiCooldownTicks--; return }
        if (player.health / player.maxHealth <= 0.2f) {
            component.zenkaiCooldownTicks = ZENKAI_COOLDOWN
            player.attributes.getInstance(Attributes.MAX_HEALTH)?.addPermanentModifier(
                AttributeModifier(ZENKAI_ID, 2.0, AttributeModifier.Operation.ADD_VALUE)
            )
        }
    }

    private fun tickNamekian(player: ServerPlayer) {
        if (player.tickCount % 40 == 0) player.heal(0.5f)
    }
}
