package com.bernardo.dragonascend.ki

class KiComponent(var current: Float = KiManager.BASE, var max: Float = KiManager.BASE) {

    fun tick(level: Int) {
        max = KiManager.max(level)
        current = KiManager.regen(current, max)
    }

    fun use(cost: Float) = KiManager.has(current, cost).also {
        if (it) current = KiManager.consume(current, cost)
    }
}
