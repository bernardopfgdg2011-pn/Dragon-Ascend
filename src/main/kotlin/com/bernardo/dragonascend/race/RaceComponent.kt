package com.bernardo.dragonascend.race

data class RaceComponent(
    val race: Race,
    var zenkaiCooldownTicks: Long = 0L,
    var zenkaiBonus: Float = 0f
)
