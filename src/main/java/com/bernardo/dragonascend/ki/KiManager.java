package com.bernardo.dragonascend.ki;

public final class KiManager {

    public static final float BASE = 100f;
    public static final float REGEN = 0.05f;
    public static final float PER_LEVEL = 10f;

    private KiManager() {
    }

    public static float max(int level) {
        return BASE + level * PER_LEVEL;
    }

    public static float regen(float ki, float max) {
        return Math.min(ki + REGEN, max);
    }

    public static float consume(float ki, float cost) {
        return Math.max(ki - cost, 0f);
    }

    public static boolean has(float ki, float cost) {
        return ki >= cost;
    }
}
