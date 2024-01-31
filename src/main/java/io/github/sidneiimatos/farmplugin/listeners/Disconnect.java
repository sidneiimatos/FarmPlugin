package io.github.sidneiimatos.farmplugin.listeners;

import io.github.sidneiimatos.farmplugin.database.Metodos;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Disconnect implements Listener {
    @EventHandler
    public void aoDesconect(PlayerQuitEvent e) {
        Bukkit.getConsoleSender().sendMessage("AAAAAAA");
        Metodos.setFarming(e.getPlayer().getName(), false);
    }
}
