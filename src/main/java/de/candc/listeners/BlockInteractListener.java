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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BlockInteractListener implements Listener {

    private List<Integer> newVersionEggIDList;

    public BlockInteractListener() {
        newVersionEggIDList = new ArrayList<>();
        newVersionEggIDList.add(14607);
        newVersionEggIDList.add(4759);
        newVersionEggIDList.add(23341);
        newVersionEggIDList.add(5462);
        newVersionEggIDList.add(27248);
        newVersionEggIDList.add(14761);
        newVersionEggIDList.add(9653);
        newVersionEggIDList.add(20787);
        newVersionEggIDList.add(14513);
        newVersionEggIDList.add(19368);
        newVersionEggIDList.add(11418);
        newVersionEggIDList.add(29488);
        newVersionEggIDList.add(16617);
        newVersionEggIDList.add(21271);
        newVersionEggIDList.add(9970);
        newVersionEggIDList.add(20113);
        newVersionEggIDList.add(25981);
        newVersionEggIDList.add(20178);
        newVersionEggIDList.add(23640);
        newVersionEggIDList.add(26638);
        newVersionEggIDList.add(22125);
        newVersionEggIDList.add(11229);
        newVersionEggIDList.add(30080);
        newVersionEggIDList.add(23614);
        newVersionEggIDList.add(24648);
        newVersionEggIDList.add(22584);
        newVersionEggIDList.add(17015);
        newVersionEggIDList.add(24573);
        newVersionEggIDList.add(26496);
        newVersionEggIDList.add(18739);
        newVersionEggIDList.add(24488);
        newVersionEggIDList.add(31848);
        newVersionEggIDList.add(14537);
        newVersionEggIDList.add(21356);
        newVersionEggIDList.add(15261);
        newVersionEggIDList.add(6550);
        newVersionEggIDList.add(14984);
        newVersionEggIDList.add(10682);
        newVersionEggIDList.add(30153);
        newVersionEggIDList.add(19713);
        newVersionEggIDList.add(17324);
        newVersionEggIDList.add(27751);
        newVersionEggIDList.add(30348);
        newVersionEggIDList.add(25324);
        newVersionEggIDList.add(11837);
        newVersionEggIDList.add(10073);
        newVersionEggIDList.add(21692);
        newVersionEggIDList.add(4275);
        newVersionEggIDList.add(11531);
        newVersionEggIDList.add(5814);
        newVersionEggIDList.add(10311);
    }

    @EventHandler
    public void onBlockInteract(@NotNull PlayerInteractEvent e) {
        if(!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            return;
        }

        if(!(newVersionEggIDList.contains(e.getClickedBlock().getType().getId()))) {
            return;
        }

        Player p = e.getPlayer();

        /*
        !IMPORTANT! Change the following void to the following code for lower versions than 1.13

        !(e.getClickedBlock().getType() == Material.MOB_SPAWNER)

        !(p.getInventory().getItemInMainHand().getType() == Material.MONSTER_EGG)

        code for 1.13+


        !(newVersionEggIDList.contains(e.getClickedBlock().getType().getId()))

        !(newVersionEggIDList.contains(p.getInventory().getItemInMainHand().getType().getId()))
         */

        if(!(newVersionEggIDList.contains(p.getInventory().getItemInHand().getType().getId()))) {
            return;
        }

        if(p.hasPermission("silkspawners.change")) {
            return;
        }

        e.setCancelled(true);
    }
}
