package io.github.sidneiimatos.farmplugin.commands;

import io.github.sidneiimatos.farmplugin.inventory.FarmInventory;
import io.github.sidneiimatos.farmplugin.utils.API;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FarmCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player)sender;
        if (args.length > 0) {
            if (!sender.hasPermission("farm.admin")) {
                sender.sendMessage("Você não tem permissão para usar este comando!");
            } else if (args[0].equalsIgnoreCase("setentrada") || args[0].equalsIgnoreCase("setsaida"))  {
                sender.sendMessage("Local definido com sucesso!");
                API.setWarp(args[0], p.getLocation());
                return false;
            } else {
                p.sendMessage("§cUtilize /farm <setentrada> ou <setsaida>");
            }
        } else if (args.length == 0) {
            new FarmInventory(p);
        }
        return false;
    }
}
