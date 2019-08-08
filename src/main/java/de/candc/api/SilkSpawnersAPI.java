package de.candc.api;

/*
 *  Copyright © 2018
 *  This file was created by Cornelius May on 03.10.2018
 */

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public interface SilkSpawnersAPI {

    /**
     * Use this method to get a spawner ItemStack
     *
     * @param spawnedEntity The entity which should be spawned by the spawner
     * @return The spawner ItemStack
     */

    ItemStack getSpawner(EntityType spawnedEntity);
}
