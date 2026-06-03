package com.bernardo.dragonascend.ki

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.server.MinecraftServer

object KiPlayerManager {
    private val kiMap = HashMap<String, KiComponent>()

    fun register() {
        ServerTickEvents.END_SERVER_TICK.register { server: MinecraftServer ->
            val players = server.playerList.players
            for (i in 0 until players.size) {
                val player = players[i]
                val uuid = player.stringUUID
                val ki = kiMap.getOrPut(uuid) { KiComponent() }
                ki.tick(player.experienceLevel)
            }
        }
    }

    fun getKi(uuid: String) = kiMap.getOrPut(uuid) { KiComponent() }
    fun removeKi(uuid: String) = kiMap.remove(uuid)
}
