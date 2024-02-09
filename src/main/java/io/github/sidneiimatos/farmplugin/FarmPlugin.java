package io.github.sidneiimatos.farmplugin;

import io.github.sidneiimatos.farmplugin.commands.FarmCommand;
import io.github.sidneiimatos.farmplugin.database.Metodos;
import io.github.sidneiimatos.farmplugin.database.MySQL;
import io.github.sidneiimatos.farmplugin.farm.FarmCache;
import io.github.sidneiimatos.farmplugin.farm.RewardCache;
import io.github.sidneiimatos.farmplugin.farm.RewardObject;
import io.github.sidneiimatos.farmplugin.farm.ToolCache;
import io.github.sidneiimatos.farmplugin.listeners.InventoryClick;
import io.github.sidneiimatos.farmplugin.listeners.Player;
import io.github.sidneiimatos.farmplugin.listeners.breakBlocks;
import io.github.sidneiimatos.farmplugin.utils.ConfigAccesor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class FarmPlugin extends JavaPlugin {
    public static FarmPlugin instance;
    public static ConfigAccesor config;

    private FarmCache farmcache;

    private RewardCache rewardCache;
    private ToolCache toolCache;
    private MySQL mysql;

    @Override
    public void onEnable() {
        instance = this;
        this.mysql = new MySQL("root", "localhost", "legado", "", this);
        this.mysql.startConnection();
        this.farmcache = new FarmCache();
        this.rewardCache = new RewardCache();
        this.toolCache = new ToolCache();
        Metodos.loadDataBase();
        config = new ConfigAccesor(this, "config.yml");
        config.saveDefaultConfig();
        loadRewards();
        /*for (String key : config.getConfig().getConfigurationSection("recompensas").getKeys(false)) {
            String title = config.getConfig().getString("recompensas."+key+".title");
            String sub_title = config.getConfig().getString("recompensas."+key+".sub_title");
            Bukkit.getConsoleSender().sendMessage(title);
            Bukkit.getConsoleSender().sendMessage(sub_title);
        }*/
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
        pluginManager.registerEvents((Listener) new Player(), this);
        pluginManager.registerEvents((Listener) new InventoryClick(), this);
        pluginManager.registerEvents((Listener) new breakBlocks(), this);
    }

    public static FarmPlugin getInstance() {
        return instance;
    }

    public FarmCache getFarmcache() {
        return farmcache;
    }

    public ToolCache getToolCache() {
        return toolCache;
    }

    public RewardCache getRewardCache() {
        return rewardCache;
    }

    public static ConfigAccesor getRewardsConfig() {
        return config;
    }


    public void loadRewards() {
        for (String key : FarmPlugin.config.getConfig().getConfigurationSection("recompensas").getKeys(false)) {
            String nome = key;
            String title = FarmPlugin.config.getConfig().getString("recompensas."+key+".title").replace("&","§");
            String sub_title = FarmPlugin.config.getConfig().getString("recompensas."+key+".sub_title").replace("&","§");
            String position = FarmPlugin.config.getConfig().getString("recompensas."+key+".position");
            //Bukkit.getConsoleSender().sendMessage(key);
            //Bukkit.getConsoleSender().sendMessage(title);
            //Bukkit.getConsoleSender().sendMessage(sub_title);

            RewardObject rewardObject = new RewardObject(nome, title, sub_title, Integer.parseInt(position));
            FarmPlugin.getInstance().getRewardCache().getCache().put(nome, rewardObject);
        }
    }
}
