package io.github.sidneiimatos.farmplugin.inventory;

import io.github.sidneiimatos.farmplugin.item.Tool;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ToolInventory {
    public ToolInventory(Player p) {
        p.closeInventory();
        Inventory inv = Bukkit.createInventory(null, 54, "Farm - Ferramenta");
        p.openInventory(inv);
    }
}
