package io.github.sidneiimatos.farmplugin.listeners;

import io.github.sidneiimatos.farmplugin.FarmPlugin;
import io.github.sidneiimatos.farmplugin.database.Metodos;
import io.github.sidneiimatos.farmplugin.inventory.ToolInventory;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class Player implements Listener {
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
        org.bukkit.entity.Player p = (org.bukkit.entity.Player) e.getWhoClicked();
        if (e.getInventory().getName().equals("RankUP - Farm") || e.getInventory().getName().equals("Vendas") || e.getInventory().getName().equals("Recompensas")) {
            e.setCancelled(false);
        } else {
            if (FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName()).isFarming()) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void openTool(PlayerInteractEvent e) {
        org.bukkit.entity.Player player = e.getPlayer();
        if (FarmPlugin.getInstance().getFarmcache().getCache().get(player.getName()).isFarming()) {
            ItemStack item = player.getItemInHand();
            if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) {
                return;
            }
            if (item != null && item.getType() == Material.DIAMOND_AXE /*&& item.hasItemMeta() && item.getItemMeta().hasLore()*/) {
                e.setCancelled(true);
                new ToolInventory(player);
                //openPick(player, item);
                return;
            }
        }
    }
}
