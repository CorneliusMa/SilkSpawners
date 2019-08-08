package de.candc.api.inventory;

/*
 Created by CandC_9_12 on 16.12.2017
*/

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemBuilder {

    private ItemStack stack;
    private ItemMeta meta;

    public ItemBuilder(Material material, int amount) {
        this.stack = new ItemStack(material, amount);
        this.meta = this.stack.getItemMeta();
    }

    public ItemBuilder(Material material) {
        this.stack = new ItemStack(material, 1);
        this.meta = this.stack.getItemMeta();
    }

    public ItemBuilder setDisplayName(String displayName) {
        this.meta.setDisplayName(displayName);
        return this;
    }

    public ItemBuilder addToLore(String lore) {
        ArrayList<String> loreList = this.meta.getLore() == null ? new ArrayList() : (ArrayList) this.meta.getLore();
        loreList.add(lore);
        this.meta.setLore(loreList);
        return this;
    }

    public ItemStack build() {
        this.stack.setItemMeta(this.meta);
        return this.stack;
    }
}
