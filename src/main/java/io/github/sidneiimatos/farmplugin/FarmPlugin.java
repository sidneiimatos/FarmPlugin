package io.github.sidneiimatos.farmplugin;

import io.github.sidneiimatos.farmplugin.commands.FarmCommand;
import io.github.sidneiimatos.farmplugin.database.Metodos;
import io.github.sidneiimatos.farmplugin.database.MySQL;
import io.github.sidneiimatos.farmplugin.farm.FarmCache;
import io.github.sidneiimatos.farmplugin.farm.FarmObject;
import io.github.sidneiimatos.farmplugin.listeners.Disconnect;
import io.github.sidneiimatos.farmplugin.listeners.InventoryClick;
import io.github.sidneiimatos.farmplugin.listeners.PlayerJoin;
import io.github.sidneiimatos.farmplugin.listeners.breakBlocks;
import io.github.sidneiimatos.farmplugin.utils.ConfigAccesor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class FarmPlugin extends JavaPlugin {
    public static FarmPlugin instance;
    public static ConfigAccesor config;

    private FarmCache farmcache;
    private MySQL mysql;

    @Override
    public void onEnable() {
        instance = this;
        this.mysql = new MySQL("root", "localhost", "legado", "", this);
        this.mysql.startConnection();
        this.farmcache = new FarmCache();
        Metodos.loadDataBase();
        config = new ConfigAccesor(this, "config.yml");
        config.saveDefaultConfig();
        getAllCommands();
        getAllEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void getAllCommands() {
        getCommand("farm").setExecutor((CommandExecutor)new FarmCommand());
    }
    public void getAllEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents((Listener) new PlayerJoin(), this);
        pluginManager.registerEvents((Listener) new InventoryClick(), this);
        pluginManager.registerEvents((Listener) new breakBlocks(), this);
        pluginManager.registerEvents((Listener) new Disconnect(), this);
    }

    public static FarmPlugin getInstance() {
        return instance;
    }

    public FarmCache getFarmcache() {
        return farmcache;
    }
}
