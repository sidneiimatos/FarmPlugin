package io.github.sidneiimatos.farmplugin.listeners;

import io.github.sidneiimatos.farmplugin.FarmPlugin;
import io.github.sidneiimatos.farmplugin.database.Metodos;
import io.github.sidneiimatos.farmplugin.farm.FarmObject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void aoEntrar(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();
        boolean a = FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName()).isFarming();
        e.setJoinMessage(""+a);
        //Metodos.createAccount(p.getPlayer().getUniqueId().toString(), p.getPlayer().getName());
        if (!Metodos.existAccount(p.getPlayer().getName())) {
            Metodos.createAccount(p.getPlayer().getUniqueId().toString(), p.getPlayer().getName());
        }
    }
}
