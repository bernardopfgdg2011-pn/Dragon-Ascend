package com.bernardo.dragonascend

import com.bernardo.dragonascend.ki.KiPlayerManager
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object DragonAscend : ModInitializer {
    const val MOD_ID = "dragon_ascend"
    val LOGGER = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        KiPlayerManager.register()
        LOGGER.info("Dragon Ascend initialized!")
    }
}
