package com.abysszrev.easywaytoget;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = easywaytoget.MODID)
public class ItemTooltipHandler {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        if (event.getItemStack().is(CoreOfTemplate.COT.get())) {
            event.getToolTip().add(Component.translatable("tooltip.easywaytoget.coreoftemplate.desc")
                    .withStyle(ChatFormatting.GRAY));
//            event.getToolTip().add(Component.translatable("tooltip.easywaytoget.coreoftemplate.hint")
//                    .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
    }
}