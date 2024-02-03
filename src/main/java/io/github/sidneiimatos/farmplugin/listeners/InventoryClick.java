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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void aoClickar(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equals("RankUP - Farm")) {
            e.setCancelled(true);
            e.setResult(Event.Result.DENY);
            Player p = (Player) e.getWhoClicked();
            int slot = e.getSlot();
            switch (slot) {
                case 11:
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aIr Farmar!")) {
                        Inventory playerInventory = p.getInventory();
                        if (isSlot1Empty(playerInventory) == true) {
                            p.sendMessage("§aTeleportado com sucesso para Farm!");
                            FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName()).setFarming(true);
                            p.teleport(API.getWarp("setentrada"));
                            //FarmPlugin.inventarios_abertos.put(p, "mina");
                            API.sendActionbar(p, "§6§lFARM §8>> §floja.rede-legado.com");
                            p.sendTitle("§6§lFARM", "§fTeleportado com sucesso para farm!");
                        } else {
                            p.sendMessage("§cVocê precisa liberar o slot 1, 2, 3 para conseguir farmar!");
                        }
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cSair da Farm!")) {
                        p.sendMessage("§aTeleportado com sucesso para o Spawn!");
                        FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName()).setFarming(false);
                        p.teleport(API.getWarp("setsaida"));
                        p.sendTitle("§6§lFARM", "§fVocê saiu da farm!");
                    }
                    /*p.teleport(API.getWarp("setentrada"));
                    FarmPlugin.inventarios_abertos.put(p, "mina");
                    API.sendActionbar(p, "§6§lFARM §8>> §floja.rede-legado.com");
                    p.sendTitle("§6§lFARM","§fTeleportado com sucesso para farm!");*/
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

    public static boolean getSpaceInInventory(Player p) {
        if (p.getInventory().getContents().length == 1) {
            if (p.getInventory().getViewers().equals("")) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isSlot1Empty(Inventory inventory) {
        for (int x = 0; x < 3; x++) {
            ItemStack itemStack = inventory.getItem(x);
            if (!(itemStack == null)) {
                return false;
            }
        }
        return true;
    }
}
