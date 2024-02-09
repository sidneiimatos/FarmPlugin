package io.github.sidneiimatos.farmplugin.listeners;

import io.github.sidneiimatos.farmplugin.FarmPlugin;
import io.github.sidneiimatos.farmplugin.farm.FarmObject;
import io.github.sidneiimatos.farmplugin.farm.RewardCache;
import io.github.sidneiimatos.farmplugin.farm.RewardObject;
import io.github.sidneiimatos.farmplugin.farm.ToolObject;
import io.github.sidneiimatos.farmplugin.utils.API;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class breakBlocks implements Listener {

    private static double porcentagem_rewards = FarmPlugin.config.getConfig().getDouble("porcentagem");

    private static String GIVE_CASH = "cash dar %player 1000";
    private static String GIVE_BOX = "mbox give {player} avancada 1";
    private static String GIVE_KEY = "crates give {player} avancada 1";
    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName()).isFarming()) {
            Block block = e.getBlock();
            String world = FarmPlugin.config.getConfig().getString("warp.setentrada.world");
            if (block.getWorld().getName().equals(world)) {
                e.setCancelled(true);
                ItemStack item = p.getItemInHand();
                if (block.getType().equals(Material.MELON_BLOCK) || block.getType().equals(Material.SUGAR_CANE_BLOCK) || block.getType().equals(Material.NETHER_WARTS)) {
                    if (item != null && item.hasItemMeta() && item.getType() == Material.DIAMOND_AXE && item.getItemMeta().getDisplayName().equals("§eFerramenta")) {
                        getEnchant(p);
                        FarmObject f = FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName());
                        f.setBlocos(1);
                        if (percentChance(porcentagem_rewards)) {
                            randomAward(p);
                        }
                        if (block.getType().equals(Material.MELON_BLOCK)) {
                            //e.setCancelled(true);
                            API.sendActionbar(p, "§cFarmando Melancia!");
                            f.addDrops("melon", 1);
                        } else if (block.getType().equals(Material.SUGAR_CANE_BLOCK)) {
                            //e.setCancelled(true);
                            API.sendActionbar(p, "§aFarmando Cana!");
                            f.addDrops("sugar", 1);
                        } else if (block.getType().equals(Material.NETHER_WARTS)) {
                            byte data = block.getData();
                            //e.setCancelled(true);
                            if (data >= 3) {
                                block.setData((byte) 0);
                                API.sendActionbar(p, "§cFarmando fungo!");
                                f.addDrops("fungo", 1);
                            }
                        }
                    }
                    else {
                        //e.setCancelled(true);
                        p.sendMessage("§cNão e possivel quebrar sem utilizar sua ferramenta!");
                    }
                } else {
                    p.sendMessage("Você não tem permissão para quebrar este bloco!");
                }

                /*if (item != null && item.hasItemMeta() && item.getType() == Material.DIAMOND_AXE && item.getItemMeta().getDisplayName().equals("§eFerramenta")) {
                    getEnchant(p);
                    FarmObject f = FarmPlugin.getInstance().getFarmcache().getCache().get(p.getName());
                    f.setBlocos(1);
                    if (percentChance(porcentagem_rewards)) {
                        randomAward(p);
                    }
                    if (block.getType().equals(Material.MELON_BLOCK)) {
                        //e.setCancelled(true);
                        API.sendActionbar(p, "§cFarmando Melancia!");
                        f.addDrops("melon", 1);
                    } else if (block.getType().equals(Material.SUGAR_CANE_BLOCK)) {
                        //e.setCancelled(true);
                        API.sendActionbar(p, "§aFarmando Cana!");
                        f.addDrops("sugar", 1);
                    } else if (block.getType().equals(Material.NETHER_WARTS)) {
                        byte data = block.getData();
                        //e.setCancelled(true);
                        if (data >= 3) {
                            block.setData((byte) 0);
                            API.sendActionbar(p, "§cFarmando fungo!");
                            f.addDrops("fungo", 1);
                        }
                    }
                }
                else {
                    //e.setCancelled(true);
                    p.sendMessage("§cNão e possivel quebrar sem utilizar sua ferramenta!");
                }*/

            }
        }
    }


    //Este Metodo da uma chance ao quebrar o bloco de ganhar um item
    protected final boolean percentChance(double percent) {
        if (percent < 0.0D || percent > 100.0D)
            throw new IllegalArgumentException("A percentagem nao pode ser maior do que 100 nem menor do que 0");
        double result = (new Random()).nextDouble() * 100.0D;
        return (result <= percent);
    }

    public void randomAward(Player p) {
        //Este metodo irá sortear uma das recompensas
        Random random = new Random();
        int n = random.nextInt(3) + 1;
        String title, sub_title;
        int position;

        /*for (int i = 1; i <= 3; i++) {
            String key = "recompensa" + i;

            if (FarmPlugin.getInstance().getRewardCache().getCache().containsKey(key)) {
                title = FarmPlugin.getInstance().getRewardCache().getCache().get(key).getTitle();
                sub_title = FarmPlugin.getInstance().getRewardCache().getCache().get(key).getSub_title();
                position = FarmPlugin.getInstance().getRewardCache().getCache().get(key).getPosition();

                p.sendMessage("" + position);
                p.sendTitle(title, sub_title);
            }
        }*/
        switch (n) {
            case 1:
                title = FarmPlugin.getInstance().getRewardCache().getCache().get("key").getTitle();
                sub_title = FarmPlugin.getInstance().getRewardCache().getCache().get("key").getSub_title();
                position = FarmPlugin.getInstance().getRewardCache().getCache().get("key").getPosition();
                p.sendMessage(""+position);
                p.sendTitle(title, sub_title);
                break;
            case 2:
                title = FarmPlugin.getInstance().getRewardCache().getCache().get("caixa").getTitle();
                sub_title = FarmPlugin.getInstance().getRewardCache().getCache().get("caixa").getSub_title();
                position = FarmPlugin.getInstance().getRewardCache().getCache().get("caixa").getPosition();
                p.sendMessage(""+position);
                p.sendTitle(title, sub_title);
                break;
            case 3:
                title = FarmPlugin.getInstance().getRewardCache().getCache().get("cash").getTitle();
                sub_title = FarmPlugin.getInstance().getRewardCache().getCache().get("cash").getSub_title();
                position = FarmPlugin.getInstance().getRewardCache().getCache().get("cash").getPosition();
                p.sendMessage(""+position);
                p.sendTitle(title, sub_title);
                break;
        }
    }

    public ToolObject getEnchant(Player p) {
        Random random = new Random();
        int n = random.nextInt(4) + 1;
        switch (n) {
            case 1:
                FarmPlugin.getInstance().getToolCache().getCache().get(p.getName()).setEficiency(1);
                break;
            case 2:
                FarmPlugin.getInstance().getToolCache().getCache().get(p.getName()).setFortune(1);
                break;
            case 3:
                FarmPlugin.getInstance().getToolCache().getCache().get(p.getName()).setPressa(1);
                break;
            case 4:
                FarmPlugin.getInstance().getToolCache().getCache().get(p.getName()).setSortudo(1);
                break;
        }
        return null;
    }

}
