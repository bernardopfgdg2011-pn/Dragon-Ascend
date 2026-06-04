package com.bernardo.dragonascend.ki;

public class KiComponent {

    private float current = KiManager.BASE;
    private float max = KiManager.BASE;

    public KiComponent() {
    }

    public KiComponent(float current, float max) {
        this.current = current;
        this.max = max;
    }

    public void tick(int level) {
        max = KiManager.max(level);
        current = KiManager.regen(current, max);
    }

    public boolean use(float cost) {
        boolean canUse = KiManager.has(current, cost);
        if (canUse) {
            current = KiManager.consume(current, cost);
        }
        return canUse;
    }

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }
}
