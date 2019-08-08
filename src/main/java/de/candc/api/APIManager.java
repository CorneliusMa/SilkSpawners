package de.candc.api;

/*
 *  Copyright © 2018
 *  This file was created by Cornelius May on 03.10.2018
 */

import de.candc.api.inventory.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.jetbrains.annotations.Contract;

public class APIManager {

    @Getter
    private static APIManager instance;

    static {
        instance = new APIManager();
    }

    @Contract(pure = true)
    private APIManager() {}

    public SilkSpawnersAPI loadAPI() {
        return spawnedEntity -> new ItemBuilder(Material.MOB_SPAWNER).addToLore("§e" + spawnedEntity.getName().substring(0, 1).toUpperCase() + spawnedEntity.getName().substring(1).toLowerCase()).build();
    }
}
