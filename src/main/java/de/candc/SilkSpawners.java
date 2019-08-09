package de.candc;

/*
 *  Copyright © 2018
 *  This file was created by Cornelius May on 20.08.2018
 */


import de.candc.api.APIManager;
import de.candc.api.SilkSpawnersAPI;
import de.candc.commands.SpawnerCommand;
import de.candc.listeners.BlockBreakListener;
import de.candc.listeners.BlockInteractListener;
import de.candc.listeners.BlockPlaceListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;

public class SilkSpawners extends JavaPlugin
{

    @Getter
    private static SilkSpawners instance;

    private static SilkSpawnersAPI api;

    /**
     * @return The SilkSpwaners API
     */

    @Contract(pure = true)
    public static SilkSpawnersAPI getApi() {
        return api;
    }

    public void onEnable() {
        //save instance for further use
        instance = this;

        //delay for a second
        getServer().getScheduler().scheduleSyncDelayedTask(this, this::startup, 1);
    }

    private void startup() {
        //log startup
        Bukkit.getConsoleSender().sendMessage("§aLoading SilkSpawners v" + getDescription().getVersion() + "!");

        //register commands
        registerCommands();

        //register events
        registerEvents();

        //load api
        api = APIManager.getInstance().loadAPI();

        //log finish
        Bukkit.getConsoleSender().sendMessage("§aLoaded SilkSpawners v" + getDescription().getVersion() + "!");
    }

    private void registerCommands() {
        getCommand("spawner").setExecutor(new SpawnerCommand());
        getCommand("spawner").setTabCompleter(new SpawnerCommand());
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new BlockPlaceListener(), this);
        pm.registerEvents(new BlockBreakListener(), this);
        pm.registerEvents(new BlockInteractListener(), this);
    }

    public static SilkSpawners getInstance()
    {
        return instance;
    }
}