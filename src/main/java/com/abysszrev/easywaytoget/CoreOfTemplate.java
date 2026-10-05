package com.abysszrev.easywaytoget;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CoreOfTemplate {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems("easywaytoget");
    public static final DeferredItem<Item> COT = ITEMS.register("coreoftemplate",
            () -> new Item(new Item.Properties()
                    .setId(ResourceKey.create(
                            Registries.ITEM,
                            Identifier.fromNamespaceAndPath("easywaytoget", "coreoftemplate")
                    ))
            )
    );
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
