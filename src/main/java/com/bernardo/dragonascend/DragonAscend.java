package com.bernardo.dragonascend;

import com.bernardo.dragonascend.ki.KiPlayerManager;
import com.bernardo.dragonascend.race.RacePassives;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DragonAscend implements ModInitializer {

    @Override
    public void onInitialize() {
        KiPlayerManager.register();
        RacePassives.register();
        LOGGER.info("Dragon Ascend initialized!");
    }

    public static final String MOD_ID = "Dragon Ascend";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
}
