package com.bernardo.dragonascend.race;

public class RaceComponent {

    public final Race race;
    public long zenkaiCooldownTicks = 0L;
    public float zenkaiBonus = 0f;

    public RaceComponent(Race race) {
        this.race = race;
    }

    public RaceComponent(Race race, long zenkaiCooldownTicks, float zenkaiBonus) {
        this.race = race;
        this.zenkaiCooldownTicks = zenkaiCooldownTicks;
        this.zenkaiBonus = zenkaiBonus;
    }
}
