package io.github.sidneiimatos.farmplugin.listeners;

import io.github.sidneiimatos.farmplugin.FarmPlugin;
import io.github.sidneiimatos.farmplugin.database.Metodos;
import io.github.sidneiimatos.farmplugin.inventory.RewardInventory;
import io.github.sidneiimatos.farmplugin.inventory.SellInventory;
import io.github.sidneiimatos.farmplugin.utils.API;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void aoClickar(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equals("RankUP - Farm")) {
            e.setCancelled(true);
            e.setResult(Event.Result.DENY);
            Player p = (Player)e.getWhoClicked();
            int slot = e.getSlot();
            switch (slot) {
                case 10:
                    p.sendMessage("Teleportar-se para farm de melancia");
                    break;
                case 11:
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aIr Farmar!")) {
                        Metodos.setFarming(p.getName(), true);
                        p.teleport(API.getWarp("setentrada"));
                        //FarmPlugin.inventarios_abertos.put(p, "mina");
                        API.sendActionbar(p, "§6§lFARM §8>> §floja.rede-legado.com");
                        p.sendTitle("§6§lFARM","§fTeleportado com sucesso para farm!");
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cSair da Farm!")) {
                        Metodos.setFarming(p.getName(), false);
                        p.teleport(API.getWarp("setsaida"));
                        p.sendTitle("§6§lFARM","§fVocê saiu da farm!");
                    }
                    /*p.teleport(API.getWarp("setentrada"));
                    FarmPlugin.inventarios_abertos.put(p, "mina");
                    API.sendActionbar(p, "§6§lFARM §8>> §floja.rede-legado.com");
                    p.sendTitle("§6§lFARM","§fTeleportado com sucesso para farm!");*/
                    break;
                case 12:
                    p.sendMessage("Teleportar-se para farm fungo");
                    break;
                case 14:
                    new SellInventory(p);
                    break;
                case 15:
                    new RewardInventory(p);
                    break;
            }
        } else if (e.getInventory().getTitle().equals("Vendas")) {
            e.setCancelled(true);
            e.setResult(Event.Result.DENY);
        }
    }
}
