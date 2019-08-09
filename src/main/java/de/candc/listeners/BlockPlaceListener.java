package de.candc.listeners;

/*
 *  Copyright © 2018
 *  This file was created by Cornelius May on 20.08.2018
 */

import de.candc.SilkSpawners;
import de.candc.events.SpawnerPlaceEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;

public class BlockPlaceListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockPlace(@NotNull BlockPlaceEvent e) {
        Player p = e.getPlayer();

        if(p.getInventory().getItemInHand() != null) {
            if(p.getInventory().getItemInHand().getType() == Material.SPAWNER) {
                if(p.getInventory().getItemInHand().getItemMeta().getLore() != null) {
                    if (e.getBlock().getType() == Material.SPAWNER && p.hasPermission("silkspawners.place")) {
                        if (!e.isCancelled()) {
                            String name = p.getInventory().getItemInHand().getItemMeta().getLore().get(0).replaceAll("§e", "");

                            Bukkit.getScheduler().runTaskLater(SilkSpawners.getInstance(), () ->
                            {
                                Block b = e.getBlockPlaced();
                                EntityType entityType = EntityType.fromName(name);

                                SpawnerPlaceEvent event = new SpawnerPlaceEvent(p, entityType, b);
                                Bukkit.getPluginManager().callEvent(event);

                                if(event.isCancelled()) {
                                    e.setCancelled(true);
                                } else {
                                    BlockState blockState = b.getState();
                                    CreatureSpawner spawner = ((CreatureSpawner) blockState);
                                    spawner.setSpawnedType(entityType);
                                    blockState.update();
                                }
                            }, 5);
                        }
                    }
                }
            }
        }
    }
}

