package com.bernardo.dragonascend.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public final class ModItems {

    public static final Item SENZU = Registry.register(
        BuiltInRegistries.ITEM,
        ResourceLocation.fromNamespaceAndPath("dragon_ascend", "senzu"),
        new Senzu(new Item.Properties())
    );

    private ModItems() {}

    public static void register() {}
}
