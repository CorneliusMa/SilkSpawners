package de.candc.commands;

/*
 *  Copyright © 2018
 *  This file was created by Cornelius May on 26.09.2018
 */

import de.candc.api.inventory.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpawnerCommand implements CommandExecutor, TabCompleter {

    private String USAGE_MESSAGE = "§ePlease use §l/spawner <Give/Take> <Player> <Mob> <Amount>";

    @Override
    public boolean onCommand(CommandSender cs, Command c, String s, String[] args) {
        if(!(cs instanceof Player)) {
            if(!(args.length == 4)) {
                cs.sendMessage(USAGE_MESSAGE);
                return false;
            }

            if(!(args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("take"))) {
                cs.sendMessage(USAGE_MESSAGE);
                return false;
            }

            Player t = Bukkit.getPlayerExact(args[1]);
            if(t == null) {
                cs.sendMessage("The player §l" + args[1]  + " §cdoes not exist.");
                return false;
            }

            EntityType entityType = EntityType.fromName(args[2]);
            if(entityType == null) {
                cs.sendMessage("§cThere is no entity with name " + args[2]);
                return false;
            }

            int amount;
            try {
                amount = Integer.parseInt(args[3]);
            } catch (NumberFormatException ex) {
                cs.sendMessage("§cPlease give a valid number");
                return false;
            }

            if(amount < 1) {
                cs.sendMessage("§cPlease give a valid number");
                return false;
            }

            if(args[0].equalsIgnoreCase("give")) {
                ItemStack is = new ItemBuilder(Material.MOB_SPAWNER, amount).addToLore("§e" + entityType.getName().substring(0, 1).toUpperCase() + entityType.getName().substring(1).toLowerCase()).build();
                t.getInventory().addItem(is);
            } else {
                int count = 0;
                for(ItemStack is : t.getInventory().getContents()) {
                    if(is != null) {
                        if (is.getItemMeta().getLore().get(0).equalsIgnoreCase("§e" + entityType.getName())) {
                            count = count + is.getAmount();
                        }
                    }
                }

                if(count < amount) {
                    cs.sendMessage("§cThe Player " + t.getName() + " has only " + count + " spawners of that type.");
                    return false;
                }

                count = 0;
                for(ItemStack is : t.getInventory().getContents()) {
                    if(is != null) {
                        if (is.getItemMeta().getLore().get(0).equalsIgnoreCase("§e" + entityType.getName())) {
                            if(is.getAmount() > amount - count) {
                                t.getInventory().remove(is);
                                t.getInventory().addItem(new ItemBuilder(Material.MOB_SPAWNER, is.getAmount() - count).addToLore("§e" + entityType.getName().substring(0, 1).toUpperCase() + entityType.getName().substring(1).toLowerCase()).build());
                            } else {
                                t.getInventory().remove(is);
                            }
                            count = count + is.getAmount();

                        }
                    }

                    if(count >= amount) {
                        System.out.println(String.valueOf(count));
                        break;
                    }
                }
                t.updateInventory();
            }
            return false;
        }

        Player p = (Player) cs;

        if(!p.hasPermission("silkspawners.command")) {
            p.sendMessage("§cYou don't have permission to perform this command.");
            return false;
        }

        if(!(args.length == 4)) {
            p.sendMessage(USAGE_MESSAGE);
            return false;
        }

        if(!(args[0].equalsIgnoreCase("give") || args[0].equalsIgnoreCase("take"))) {
            p.sendMessage(USAGE_MESSAGE);
            return false;
        }

        Player t = Bukkit.getPlayerExact(args[1]);
        if(t == null) {
            p.sendMessage("The player §l" + args[1]  + " §cdoes not exist.");
            return false;
        }

        EntityType entityType = EntityType.fromName(args[2]);
        if(entityType == null) {
            p.sendMessage("§cThere is no entity with name " + args[2]);
            return false;
        }

        int amount;
        try {
            amount = Integer.parseInt(args[3]);
        } catch (NumberFormatException ex) {
            p.sendMessage("§cPlease give a valid number");
            return false;
        }

        if(amount < 1) {
            p.sendMessage("§cPlease give a valid number");
            return false;
        }

        if(args[0].equalsIgnoreCase("give")) {
            ItemStack is = new ItemBuilder(Material.MOB_SPAWNER, amount).addToLore("§e" + entityType.getName().substring(0, 1).toUpperCase() + entityType.getName().substring(1).toLowerCase()).build();
            t.getInventory().addItem(is);
        } else {
            int count = 0;
            for(ItemStack is : t.getInventory().getContents()) {
                if(is != null) {
                    if (is.getItemMeta().getLore().get(0).equalsIgnoreCase(entityType.getName())) {
                        count = count + is.getAmount();
                    }
                }
            }

            if(count < amount) {
                p.sendMessage("§cThe Player " + t.getName() + " has only " + count + " spawners of that type.");
                return false;
            }

            t.getInventory().remove(new ItemBuilder(Material.MOB_SPAWNER, amount).addToLore("§e" + entityType.getName().substring(0, 1).toUpperCase() + entityType.getName().substring(1).toLowerCase()).build());
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender cs, Command c, String s, String[] args) {
        List<String> list = new ArrayList<>();

        if(!(cs instanceof Player)) {
            return list;
        }

        if(!cs.hasPermission("silkspawners.command")) {
            return list;
        }

        if(args.length == 1) {
            list.add("give");
            list.add("take");
        } else if(args.length == 2) {
            for(Player p : Bukkit.getOnlinePlayers()) {
                list.add(p.getName());
            }
        } else if(args.length == 3) {
            for (EntityType type : EntityType.values()) {
                if (type.isAlive() && type.isSpawnable()) {
                    list.add(type.getName().substring(0, 1).toUpperCase() + type.getName().substring(1).toLowerCase());
                }
            }
        }

        return list;
    }
}
