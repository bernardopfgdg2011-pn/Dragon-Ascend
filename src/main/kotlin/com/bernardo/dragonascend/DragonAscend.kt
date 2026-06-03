package com.bernardo.dragonascend

import com.bernardo.dragonascend.ki.KiPlayerManager
import com.bernardo.dragonascend.race.RacePassives
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

class DragonAscend : ModInitializer {
    override fun onInitialize() {
        KiPlayerManager.register()
        RacePassives.register()
        LOGGER.info("Dragon Ascend initialized!")
    }

    companion object {
        const val MOD_ID = "dragon_ascend"
        val LOGGER = LoggerFactory.getLogger(MOD_ID)
    }
}
