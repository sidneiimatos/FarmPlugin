package io.github.sidneiimatos.farmplugin.inventory;

import io.github.sidneiimatos.farmplugin.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ToolInventory {
    static final ItemStack EFICIENCY;
    static final ItemStack FORTUNE;
    static final ItemStack REWARDS;
    static final ItemStack EXPLOSIVE;

    static {
        EFICIENCY = (new ItemBuilder("http://textures.minecraft.net/texture/eabfde8398c1102875cce8bfcc342e4fee34dccb48341978de5346c0bf545aa7")).setName("§e§lEFICIENCIA").setLore(new String[] {"","§aClique para teleportar-se!"}).toItemStack();
        FORTUNE = (new ItemBuilder("http://textures.minecraft.net/texture/eabfde8398c1102875cce8bfcc342e4fee34dccb48341978de5346c0bf545aa7")).setName("§e§lFORTUNA").setLore(new String[] {"","§aClique para teleportar-se!"}).toItemStack();
        REWARDS = (new ItemBuilder("http://textures.minecraft.net/texture/eabfde8398c1102875cce8bfcc342e4fee34dccb48341978de5346c0bf545aa7")).setName("§e§lMULTIPLICADOR DE RECOMPENSA").setLore(new String[] {"","§aClique para teleportar-se!"}).toItemStack();
        EXPLOSIVE = (new ItemBuilder("http://textures.minecraft.net/texture/eabfde8398c1102875cce8bfcc342e4fee34dccb48341978de5346c0bf545aa7")).setName("§e§lEXPLOSÃO").setLore(new String[] {"","§aClique para teleportar-se!"}).toItemStack();
    }
    public ToolInventory(Player p) {
        p.closeInventory();
        Inventory inv = Bukkit.createInventory(null, 54, "Farm - Encantamentos");
        inv.setItem(10, EFICIENCY);
        inv.setItem(11, FORTUNE);
        inv.setItem(12, REWARDS);
        inv.setItem(13, EXPLOSIVE);
        p.openInventory(inv);
    }
}
