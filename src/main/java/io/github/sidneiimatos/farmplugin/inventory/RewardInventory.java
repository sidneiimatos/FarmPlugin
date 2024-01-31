package io.github.sidneiimatos.farmplugin.inventory;

import io.github.sidneiimatos.farmplugin.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RewardInventory {
    /*static final ItemStack FUNGO;
    static final ItemStack MELON;
    static final ItemStack CANA;

    static {
        FUNGO = (new ItemBuilder(Material.getMaterial(372))).setName("§cFungo!").setLore(new String[] {"§fQuantia: §710.000","§fLimite de Venda: §71.000","","§aClique para vender!"}).setAmount(1).toItemStack();
        MELON = (new ItemBuilder(Material.MELON_BLOCK)).setName("§eMelancia!").setLore(new String[] {"§fQuantia: §710.000","§fLimite de Venda: §71.000","","§aClique para vender!"}).setAmount(1).toItemStack();
        CANA = (new ItemBuilder(Material.SUGAR_CANE)).setName("§aCana!").setLore(new String[] {"§fQuantia: §710.000","§fLimite de Venda: §71.000","","§aClique para vender!"}).setAmount(1).toItemStack();
    }*/
    public RewardInventory(Player p) {
        p.closeInventory();
        Inventory inv = Bukkit.createInventory(null, 27, "Recompensas");
        p.openInventory(inv);
    }
}
