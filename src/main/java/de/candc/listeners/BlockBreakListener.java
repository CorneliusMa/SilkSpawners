package de.candc.listeners;

/*
 *  Copyright © 2018
 *  This file was created by Cornelius May on 20.08.2018
 */

import de.candc.api.inventory.ItemBuilder;
import de.candc.events.SpawnerBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;


public class BlockBreakListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(@NotNull BlockBreakEvent e) {
        Player p = e.getPlayer();

        if(p.getInventory().getItemInHand() != null) {
            if(p.getInventory().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                if (e.getBlock().getType() == Material.MOB_SPAWNER && p.hasPermission("silkspawners.break")) {
                    if(!e.isCancelled()) {
                        e.setExpToDrop(0);

                        Block b = e.getBlock();

                        CreatureSpawner cs = (CreatureSpawner) b.getState();
                        EntityType spawnedEntity = cs.getSpawnedType();
                        ItemStack is = new ItemBuilder(Material.MOB_SPAWNER).addToLore("§e" + spawnedEntity.getName().substring(0, 1).toUpperCase() + spawnedEntity.getName().substring(1).toLowerCase()).build();
                        SpawnerBreakEvent event = new SpawnerBreakEvent(p, spawnedEntity, b);
                        Bukkit.getPluginManager().callEvent(event);
                        if(!event.isCancelled()) {
                            p.getWorld().dropItem(b.getLocation().add(0.5, 0, 0.5), is);
                        }
                    }
                }
            }
        }
    }

    private boolean isInventoryFull(Player p) {
        return p.getInventory().firstEmpty() == -1;
    }
}
