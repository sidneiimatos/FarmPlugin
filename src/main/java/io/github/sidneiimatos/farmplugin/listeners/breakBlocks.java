package io.github.sidneiimatos.farmplugin.listeners;

import io.github.sidneiimatos.farmplugin.FarmPlugin;
import io.github.sidneiimatos.farmplugin.farm.FarmObject;
import io.github.sidneiimatos.farmplugin.utils.API;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class breakBlocks implements Listener {
    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();

        /*if (player.isFarming() == true) {
            Block block = e.getBlock();
            String world = FarmPlugin.config.getConfig().getString("warp.setentrada.world");
            if (block.getWorld().getName().equals(world)) {
                if (block.getType().equals(Material.MELON_BLOCK)) {
                    e.setCancelled(true);
                    API.sendActionbar(p, "§cFarmando Melancia!");
                } else if (block.getType().equals(Material.SUGAR_CANE_BLOCK)) {
                    e.setCancelled(true);
                    API.sendActionbar(p, "§aFarmando Cana!");
                } else if (block.getType().equals(Material.NETHER_WARTS)) {
                    byte data = block.getData();
                    e.setCancelled(true);
                    if (data >= 3) {
                        block.setData((byte)0);
                        API.sendActionbar(p, "§cFarmando fungo!");
                    }
                }
            }
            String world = FarmPlugin.config.getConfig().getString("warp.setentrada.world");
            Block block = e.getBlock();
            if (block.getWorld().equals(world)) {
                p.sendMessage("A");
            }
        }*/
    }
}
