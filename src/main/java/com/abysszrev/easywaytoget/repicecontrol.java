package com.abysszrev.easywaytoget;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.minecraft.server.MinecraftServer;

@EventBusSubscriber(modid = easywaytoget.MODID)
public class repicecontrol {

    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        Player player = event.getEntity();
        ItemStack crafted = event.getCrafting();

        if (!crafted.is(CoreOfTemplate.COT.get())) return;

        if (player instanceof ServerPlayer serverPlayer) {

            if (!hasAdvancement(serverPlayer, "minecraft", "nether/summon_wither")) {

                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                    ItemStack stack = player.getInventory().getItem(i);
                    if (ItemStack.isSameItemSameComponents(stack, crafted)) {
                        player.getInventory().setItem(i, ItemStack.EMPTY);
                        break;
                    }
                }


                if (ItemStack.isSameItemSameComponents(player.containerMenu.getCarried(), crafted)) {
                    player.containerMenu.setCarried(ItemStack.EMPTY);
                }

                player.containerMenu.broadcastChanges();

                Container craftMatrix = event.getInventory();
                for (int i = 0; i < craftMatrix.getContainerSize(); i++) {
                    ItemStack slotStack = craftMatrix.getItem(i);
                    if (!slotStack.isEmpty()) {
                        player.getInventory().add(slotStack.copy());
                    }
                }


                player.containerMenu.broadcastChanges();


                serverPlayer.displayClientMessage(
                        Component.translatable("message.easywaytoget.crafting_denied"),
                        true
                );
            }
        }
    }


    private static boolean hasAdvancement(ServerPlayer player, String namespace, String path) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null) return false;

        AdvancementHolder advancement = server.getAdvancements()
                .get(Identifier.fromNamespaceAndPath(namespace, path));
        if (advancement == null) return false;

        AdvancementProgress progress = player.getAdvancements()
                .getOrStartProgress(advancement);
        return progress.isDone();
    }
}