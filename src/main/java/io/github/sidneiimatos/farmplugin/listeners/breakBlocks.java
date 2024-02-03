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

import java.util.Random;

public class breakBlocks implements Listener {
    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName()).isFarming() == true) {
            Block block = e.getBlock();
            String world = FarmPlugin.config.getConfig().getString("warp.setentrada.world");
            if (block.getWorld().getName().equals(world)) {
                FarmObject f = FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName());
                if (block.getType().equals(Material.MELON_BLOCK)) {
                    e.setCancelled(true);
                    API.sendActionbar(p, "§cFarmando Melancia!");
                    f.addDrops("melon", 1);
                } else if (block.getType().equals(Material.SUGAR_CANE_BLOCK)) {
                    e.setCancelled(true);
                    API.sendActionbar(p, "§aFarmando Cana!");
                    f.addDrops("sugar", 1);
                }
            }
            if (block.getWorld().equals(world)) {
                p.sendMessage("A");
            }
        }
    }
    @EventHandler
    public void breakNetherWarts(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName()).isFarming() == true) {
            Block block = e.getBlock();
            String world = FarmPlugin.config.getConfig().getString("warp.setentrada.world");
            if (block.getWorld().getName().equals(world)) {
                FarmObject f = FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName());
                if (block.getType().equals(Material.NETHER_WARTS)) {
                    byte data = block.getData();
                    e.setCancelled(true);
                    if (data >= 3) {
                        block.setData((byte) 0);
                        API.sendActionbar(p, "§cFarmando fungo!");
                        f.addDrops("fungo", 1);
                    }
                }
            }
        }
    }
    @EventHandler
    public void breakMelon(BlockBreakEvent e) {

    }
    @EventHandler
    public void breakSugar(BlockBreakEvent e) {
    }


    //Este Metodo da uma chance ao quebrar o bloco de ganhar um item
    protected final boolean percentChance(double percent) {
        if (percent < 0.0D || percent > 100.0D)
            throw new IllegalArgumentException("A percentagem nao pode ser maior do que 100 nem menor do que 0");
        double result = (new Random()).nextDouble() * 100.0D;
        return (result <= percent);
    }

    protected final void getGanhador(Player p) {
        //Este metodo vai ser
        for (String i : FarmPlugin.config.getConfig().getConfigurationSection("recompensas").getKeys(false)) {
            String config = "recompensas."+i;
            String title = FarmPlugin.config.getConfig().getString("recompensas."+i+".title");
            String sub_title = FarmPlugin.config.getConfig().getString("recompensas."+i+".sub_title");
            p.sendMessage(title);
            //p.sendTitle(title, "sub_title");
            //for (String comandos : FarmPlugin.config.getConfig().getStringList("recompensasn."+i+".comandos")) {
            //    p.sendMessage(comandos);
            //}
        }
        if (percentChance(100)) {
            // get recompensas
        }
    }

    protected final void sortearCash() {

    }

    public String randomAward() {
        //Este metodo irá sortear uma das recompensas
        Random random = new Random();
        int n = random.nextInt(3);
        switch (n) {
            case 1:
                return "Ganhou cash";
            case 2:
                return "Ganhou caixa";
            case 3:
                return "Ganhou key";
        }
        return null;
    }
}
