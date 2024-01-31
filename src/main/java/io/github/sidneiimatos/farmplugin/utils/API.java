package io.github.sidneiimatos.farmplugin.utils;

import io.github.sidneiimatos.farmplugin.FarmPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class API {

    public static void sendActionbar(Player player, String message) {
        if (player == null || message == null)
            return;
        String nmsVersion = Bukkit.getServer().getClass().getPackage().getName();
        nmsVersion = nmsVersion.substring(nmsVersion.lastIndexOf(".") + 1);
        try {
            Class<?> craftPlayerClass = Class.forName("org.bukkit.craftbukkit." + nmsVersion + ".entity.CraftPlayer");
            Object craftPlayer = craftPlayerClass.cast(player);
            Class<?> ppoc = Class.forName("net.minecraft.server." + nmsVersion + ".PacketPlayOutChat");
            Class<?> packet = Class.forName("net.minecraft.server." + nmsVersion + ".Packet");
            Class<?> chat = Class.forName("net.minecraft.server." + nmsVersion + (nmsVersion.equalsIgnoreCase("v1_8_R1") ? ".ChatSerializer" : ".ChatComponentText"));
            Class<?> chatBaseComponent = Class.forName("net.minecraft.server." + nmsVersion + ".IChatBaseComponent");
            Method method = null;
            if (nmsVersion.equalsIgnoreCase("v1_8_R1"))
                method = chat.getDeclaredMethod("a", new Class[] { String.class });
            Object object = nmsVersion.equalsIgnoreCase("v1_8_R1") ? chatBaseComponent.cast(method.invoke(chat, new Object[] { "{'text': '" + message + "'}" })) : chat.getConstructor(new Class[] { String.class }).newInstance(new Object[] { message });
            Object packetPlayOutChat = ppoc.getConstructor(new Class[] { chatBaseComponent, byte.class }).newInstance(new Object[] { object, Byte.valueOf((byte)2) });
            Method handle = craftPlayerClass.getDeclaredMethod("getHandle", new Class[0]);
            Object iCraftPlayer = handle.invoke(craftPlayer, new Object[0]);
            Field playerConnectionField = iCraftPlayer.getClass().getDeclaredField("playerConnection");
            Object playerConnection = playerConnectionField.get(iCraftPlayer);
            Method sendPacket = playerConnection.getClass().getDeclaredMethod("sendPacket", new Class[] { packet });
            sendPacket.invoke(playerConnection, new Object[] { packetPlayOutChat });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void setWarp(String warp, Location loc) {
        String prefix = "warp." + warp + ".";
        FarmPlugin.config.getConfig().set(String.valueOf(prefix) + "world", loc.getWorld().getName());
        FarmPlugin.config.getConfig().set(String.valueOf(prefix) + "x", loc.getX());
        FarmPlugin.config.getConfig().set(String.valueOf(prefix) + "y", loc.getY());
        FarmPlugin.config.getConfig().set(String.valueOf(prefix) + "z", loc.getZ());
        FarmPlugin.config.getConfig().set(String.valueOf(prefix) + "yaw", loc.getYaw());
        FarmPlugin.config.getConfig().set(String.valueOf(prefix) + "pitch", loc.getPitch());
        FarmPlugin.config.saveConfig();
    }

    /*public static Location getWarp(String warp) {
        try {
            String prefix = "warp."+ warp + ".";
            return new Location(Bukkit.getWorld(FarmPlugin.config.getConfig().getString(String.valueOf(prefix) + "world")),
                    FarmPlugin.config.getConfig().getDouble(String.valueOf(prefix)+ "x"), FarmPlugin.config.getConfig().getDouble(String.valueOf(prefix)+ "y"),
                    FarmPlugin.config.getConfig().getDouble(String.valueOf(prefix)+ "z"), (float)FarmPlugin.config.getConfig().getDouble(String.valueOf(prefix)+ "yaw"), (float)FarmPlugin.config.getConfig().getDouble(String.valueOf(prefix)+ "pitch"));
        } catch (NullPointerException nullPointerException) {
            return null;
        }
    }*/

    public static Location getWarp(String warp) {
         try {
               String prefix = "warp."+ warp + ".";
               return new Location(
                       Bukkit.getWorld(FarmPlugin.config.getConfig().getString(String.valueOf(prefix) + "world")),
                       FarmPlugin.config.getConfig().getDouble(String.valueOf(prefix) + "x"),
                       FarmPlugin.config.getConfig().getDouble(String.valueOf(prefix) + "y"),
                       FarmPlugin.config.getConfig().getDouble(String.valueOf(prefix) + "z"),
                       (float)FarmPlugin.config.getConfig().getDouble(String.valueOf(prefix) + "yaw"),
                       (float)FarmPlugin.config.getConfig().getDouble(String.valueOf(prefix) + "pitch"));
             } catch (NullPointerException nullPointerException) {
               return null;
             }
       }
}
