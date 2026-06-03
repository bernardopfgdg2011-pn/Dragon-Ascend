package com.bernardo.dragonascend.ki

object KiManager {
    const val BASE = 100f
    const val REGEN = 0.05f
    const val PER_LEVEL = 10f

    fun max(level: Int) = BASE + level * PER_LEVEL
    fun regen(ki: Float, max: Float) = (ki + REGEN).coerceAtMost(max)
    fun consume(ki: Float, cost: Float) = (ki - cost).coerceAtLeast(0f)
    fun has(ki: Float, cost: Float) = ki >= cost
}
