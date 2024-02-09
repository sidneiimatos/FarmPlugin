package io.github.sidneiimatos.farmplugin.inventory;

import io.github.sidneiimatos.farmplugin.FarmPlugin;
import io.github.sidneiimatos.farmplugin.database.Metodos;
import io.github.sidneiimatos.farmplugin.farm.FarmObject;
import io.github.sidneiimatos.farmplugin.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SellInventory {
    static final ItemStack SELL_ALL;
    static final ItemStack SELL_ALL_NO_VIP;

    static {
        //FUNGO = (new ItemBuilder(Material.getMaterial(372))).setName("§cFungo!").setLore(new String[] {"§fQuantia: §710.000","§fLimite de Venda: §71.000","","§aClique para vender!"}).setAmount(1).toItemStack();
        //MELON = (new ItemBuilder(Material.MELON_BLOCK)).setName("§eMelancia!").setLore(new String[] {"§fQuantia: §710.000","§fLimite de Venda: §71.000","","§aClique para vender!"}).setAmount(1).toItemStack();
        //CANA = (new ItemBuilder(Material.SUGAR_CANE)).setName("§aCana!").setLore(new String[] {"§fQuantia: §710.000","§fLimite de Venda: §71.000","","§aClique para vender!"}).setAmount(1).toItemStack();
        SELL_ALL = (new ItemBuilder(Material.getMaterial(341))).setName("§aVenda Tudo!").setLore(new String[] {"§7 Está opção é permitida apenas","§7 para jogadores que adquiriram","§7 VIP em nossa loja!","", "§fAcesse: §7loja.rede-legado.com"}).setAmount(1).toItemStack();
        SELL_ALL_NO_VIP = (new ItemBuilder(Material.BARRIER)).setName("§c§mVenda Tudo!").setLore(new String[] {"§7 Está opção é permitida apenas","§7 para jogadores que adquiriram","§7 VIP em nossa loja!","", "§fAcesse: §7loja.rede-legado.com"}).setAmount(1).toItemStack();
        //BACK_MENU = (new ItemBuilder(Material.getMaterial(351))).setName("§aVenda Tudo!").setLore(new String[] {"§7 Está opção é permitida apenas","§7 para jogadores que adquiriram","§7 VIP em nossa loja!","", "§fAcesse: §7loja.rede-legado.com"}).setAmount(1).toItemStack();
    }

    public SellInventory(Player p) {
        p.closeInventory();
        Inventory inv = Bukkit.createInventory(null, 36, "Vendas");
        int blocos = FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName()).getBlocos();
        int limite = FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName()).getLimite();
        int fungo = FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName()).getFungo();
        int cana = FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName()).getSugar();
        int melancia = FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName()).getMelon();
        inv.setItem(12, (new ItemBuilder(Material.MELON_BLOCK)).setName("§eMelancia! §7["+blocos+"]").setLore(new String[] {"§fQuantia: §7"+melancia,"§fLimite de Venda: §7"+limite,"","§aClique para vender!"}).setAmount(1).toItemStack());
        inv.setItem(13, (new ItemBuilder(Material.SUGAR_CANE)).setName("§aCana! §7["+blocos+"]").setLore(new String[] {"§fQuantia: §7"+cana,"§fLimite de Venda: §7"+limite,"","§aClique para vender!"}).setAmount(1).toItemStack());
        inv.setItem(14, (new ItemBuilder(Material.getMaterial(372))).setName("§cFungo! §7["+blocos+"]").setLore(new String[] {"§fQuantia: §7"+fungo,"§fLimite de Venda: §7"+limite,"","§aClique para vender!"}).setAmount(1).toItemStack());
        if (p.hasPermission("legado.vip")) {
            inv.setItem(31, SELL_ALL);
        } else {
            inv.setItem(31, SELL_ALL_NO_VIP);
        }
        p.openInventory(inv);
    }
}
