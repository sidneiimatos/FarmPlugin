package io.github.sidneiimatos.farmplugin.inventory;

import io.github.sidneiimatos.farmplugin.FarmPlugin;
import io.github.sidneiimatos.farmplugin.database.Metodos;
import io.github.sidneiimatos.farmplugin.farm.FarmObject;
import io.github.sidneiimatos.farmplugin.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FarmInventory {
    static final ItemStack JOIN;
    static final ItemStack LEAVE;
    static final ItemStack FUNGO;
    static final ItemStack MELON;
    static final ItemStack CANA;
    static final ItemStack DROPS;
    static final ItemStack REWARDS;
    static final ItemStack TOP;

    static {
        JOIN = (new ItemBuilder(Material.getMaterial(290))).setGlow(true).setName("§aIr Farmar!").setLore(new String[] {"","§aClique para teleportar-se!"}).setAmount(1).toItemStack();
        LEAVE = (new ItemBuilder(Material.BARRIER)).setName("§cSair da Farm!").setLore(new String[] {"","§aClique para teleportar-se!"}).setAmount(1).toItemStack();
        FUNGO = (new ItemBuilder(Material.getMaterial(372))).setName("§aFarm de Fungo!").setLore(new String[] {"","§aClique para teleportar-se!"}).setAmount(1).toItemStack();
        MELON = (new ItemBuilder(Material.MELON_BLOCK)).setName("§aFarm de Melancia!").setLore(new String[] {"","§aClique para teleportar-se!"}).setAmount(1).toItemStack();
        CANA = (new ItemBuilder(Material.SUGAR_CANE)).setName("§aFarm de Cana!").setLore(new String[] {"","§aClique para teleportar-se!"}).setAmount(1).toItemStack();
        DROPS = (new ItemBuilder(Material.getMaterial(154))).setName("§aVenda seus Drops!").setLore(new String[] {"","§aClique para teleportar-se!"}).setAmount(1).toItemStack();
        REWARDS = (new ItemBuilder(Material.getMaterial(130))).setName("§aColete suas recompensas!").setLore(new String[] {"","§aClique para teleportar-se!"}).setAmount(1).toItemStack();
        TOP = (new ItemBuilder(Material.getMaterial(425))).setDurability((short)15).setName("§aTOP Jogadores!").setLore(new String[] {"","§aClique para ver os TOP jogadores!"}).setAmount(1).toItemStack();
    }
    public FarmInventory(Player p) {
        p.closeInventory();
        Inventory inv = Bukkit.createInventory(null, 27, "RankUP - Farm");
        if (FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName()).isFarming() == true) {
            inv.setItem(11, LEAVE);
        } else {
            inv.setItem(11, JOIN);
        }
        //inv.setItem(11, JOIN);
        /*inv.setItem(10, MELON);
        inv.setItem(11, CANA);
        inv.setItem(12, FUNGO);*/
        inv.setItem(14, DROPS);
        inv.setItem(15, REWARDS);
        inv.setItem(16, TOP);
        p.openInventory(inv);
    }
}
