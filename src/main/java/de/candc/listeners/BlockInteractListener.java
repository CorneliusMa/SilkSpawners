package de.candc.listeners;

/*
 *  Copyright © 2018
 *  This file was created by CandC_9_12 on 19.11.2018
 */

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.SpawnEggMeta;
import org.bukkit.material.SpawnEgg;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BlockInteractListener implements Listener {

    private List<Material> newVersionEggIDList;

    public BlockInteractListener()
    {
        newVersionEggIDList = new ArrayList<>();
        newVersionEggIDList.add(Material.ELDER_GUARDIAN_SPAWN_EGG);
        newVersionEggIDList.add(Material.BAT_SPAWN_EGG);
        newVersionEggIDList.add(Material.BLAZE_SPAWN_EGG);
        newVersionEggIDList.add(Material.CAT_SPAWN_EGG);
        newVersionEggIDList.add(Material.CAVE_SPIDER_SPAWN_EGG);
        newVersionEggIDList.add(Material.CHICKEN_SPAWN_EGG);
        newVersionEggIDList.add(Material.COD_SPAWN_EGG);
        newVersionEggIDList.add(Material.COW_SPAWN_EGG);
        newVersionEggIDList.add(Material.CREEPER_SPAWN_EGG);
        newVersionEggIDList.add(Material.DOLPHIN_SPAWN_EGG);
        newVersionEggIDList.add(Material.DONKEY_SPAWN_EGG);
        newVersionEggIDList.add(Material.DRAGON_EGG);
        newVersionEggIDList.add(Material.DROWNED_SPAWN_EGG);
        newVersionEggIDList.add(Material.ENDERMAN_SPAWN_EGG);
        newVersionEggIDList.add(Material.ENDERMITE_SPAWN_EGG);
        newVersionEggIDList.add(Material.EVOKER_SPAWN_EGG);
        newVersionEggIDList.add(Material.FOX_SPAWN_EGG);
        newVersionEggIDList.add(Material.GHAST_SPAWN_EGG);
        newVersionEggIDList.add(Material.GUARDIAN_SPAWN_EGG);
        newVersionEggIDList.add(Material.HORSE_SPAWN_EGG);
        newVersionEggIDList.add(Material.HUSK_SPAWN_EGG);
        newVersionEggIDList.add(Material.LLAMA_SPAWN_EGG);
        newVersionEggIDList.add(Material.MAGMA_CUBE_SPAWN_EGG);
        newVersionEggIDList.add(Material.MOOSHROOM_SPAWN_EGG);
        newVersionEggIDList.add(Material.MULE_SPAWN_EGG);
        newVersionEggIDList.add(Material.OCELOT_SPAWN_EGG);
        newVersionEggIDList.add(Material.PANDA_SPAWN_EGG);
        newVersionEggIDList.add(Material.PARROT_SPAWN_EGG);
        newVersionEggIDList.add(Material.PHANTOM_SPAWN_EGG);
        newVersionEggIDList.add(Material.PIG_SPAWN_EGG);
        newVersionEggIDList.add(Material.PILLAGER_SPAWN_EGG);
        newVersionEggIDList.add(Material.POLAR_BEAR_SPAWN_EGG);
        newVersionEggIDList.add(Material.PUFFERFISH_SPAWN_EGG);
        newVersionEggIDList.add(Material.RABBIT_SPAWN_EGG);
        newVersionEggIDList.add(Material.RAVAGER_SPAWN_EGG);
        newVersionEggIDList.add(Material.SALMON_SPAWN_EGG);
        newVersionEggIDList.add(Material.SHEEP_SPAWN_EGG);
        newVersionEggIDList.add(Material.SHULKER_SPAWN_EGG);
        newVersionEggIDList.add(Material.SILVERFISH_SPAWN_EGG);
        newVersionEggIDList.add(Material.SKELETON_HORSE_SPAWN_EGG);
        newVersionEggIDList.add(Material.SKELETON_SPAWN_EGG);
        newVersionEggIDList.add(Material.SLIME_SPAWN_EGG);
        newVersionEggIDList.add(Material.SPIDER_SPAWN_EGG);
        newVersionEggIDList.add(Material.SQUID_SPAWN_EGG);
        newVersionEggIDList.add(Material.STRAY_SPAWN_EGG);
        newVersionEggIDList.add(Material.TRADER_LLAMA_SPAWN_EGG);
        newVersionEggIDList.add(Material.TROPICAL_FISH_SPAWN_EGG);
        newVersionEggIDList.add(Material.VEX_SPAWN_EGG);
        newVersionEggIDList.add(Material.VILLAGER_SPAWN_EGG);
        newVersionEggIDList.add(Material.VINDICATOR_SPAWN_EGG);
        newVersionEggIDList.add(Material.WANDERING_TRADER_SPAWN_EGG);
        newVersionEggIDList.add(Material.WITCH_SPAWN_EGG);
        newVersionEggIDList.add(Material.WITHER_SKELETON_SPAWN_EGG);
        newVersionEggIDList.add(Material.WOLF_SPAWN_EGG);
        newVersionEggIDList.add(Material.ZOMBIE_HORSE_SPAWN_EGG);
        newVersionEggIDList.add(Material.ZOMBIE_PIGMAN_SPAWN_EGG);
        newVersionEggIDList.add(Material.ZOMBIE_SPAWN_EGG);
        newVersionEggIDList.add(Material.ZOMBIE_VILLAGER_SPAWN_EGG);
    }

    @EventHandler
    public void onBlockInteract(@NotNull PlayerInteractEvent e) {
        if(!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        if(!(newVersionEggIDList.contains(e.getPlayer().getInventory().getItemInMainHand().getType()))) {
            return;
        }

        Player p = e.getPlayer();

        if(!(e.getClickedBlock().getType() == Material.SPAWNER)) {
            return;
        }

        if(!(p.getInventory().getItemInMainHand().getType() == Material.EGG))
        {
            return;
        }

        if(p.hasPermission("silkspawners.change"))
        {
            return;
        }

        e.setCancelled(true);
    }
}
