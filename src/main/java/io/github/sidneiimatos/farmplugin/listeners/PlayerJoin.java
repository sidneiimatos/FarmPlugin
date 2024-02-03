package io.github.sidneiimatos.farmplugin.listeners;

import io.github.sidneiimatos.farmplugin.FarmPlugin;
import io.github.sidneiimatos.farmplugin.database.Metodos;
import io.github.sidneiimatos.farmplugin.farm.FarmObject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void aoEntrar(PlayerJoinEvent e) {
        if (!Metodos.existAccount(e.getPlayer().getName())) {
            Metodos.createAccount(e.getPlayer().getName());
        }
    }

    @EventHandler
    public void aoDesconect(PlayerQuitEvent e) {
        FarmPlugin.getInstance().getFarmcache().getCache().get(e.getPlayer().getName()).setFarming((boolean)false);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getInventory().getName().equals("RankUP - Farm") || e.getInventory().getName().equals("Vendas") || e.getInventory().getName().equals("Recompensas")) {
            e.setCancelled(false);
        } else {
            if (FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName()).isFarming()) {
                e.setCancelled(true);
            }
        }
    }
}
