package io.github.sidneiimatos.farmplugin.database;

import io.github.sidneiimatos.farmplugin.farm.FarmObject;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Metodos {
    private static MySQL mysql;

    public static void loadDataBase() {
        try {
            PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM farmplugin");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                FarmObject farmObject = new FarmObject(rs.getString("username"), rs.getInt("fungo_qtd"), rs.getInt("melon_qtd"), rs.getInt("sugar_qtd"), rs.getInt("blocos"), rs.getInt("limite"), rs.getBoolean("farming"));
                /*String owner = rs.getString("username");
                int fungo_qtd = rs.getInt("fungo_qtd"), melon_qtd = rs.getInt("melon_qtd"), sugar_qtd = rs.getInt("sugar_qtd"), blocos = rs.getInt("blocos"), limite = rs.getInt("limite");
                boolean farming = rs.getBoolean("farming");
                FarmObject player = new FarmObject(owner, fungo_qtd, melon_qtd, sugar_qtd, blocos, limite, farming);*/
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean existAccount(String arg) {
        try {
            PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM farmplugin WHERE username=?");
            ps.setString(1, arg);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createAccount(String uuid, String player) {
        try {
            PreparedStatement ps = MySQL.connection.prepareStatement("INSERT INTO farmplugin (username, fungo_qtd, melon_qtd, sugar_qtd, blocos, limite, farming) values (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, player);
            ps.setInt(2, 0);
            ps.setInt(3, 0);
            ps.setInt(4, 0);
            ps.setInt(5, 0);
            ps.setInt(6, 1000);
            ps.setBoolean(7, false);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getLimite(String player) {
        checkConnection();
        try {
            PreparedStatement ps = MySQL.connection.prepareStatement("SELECT limite FROM farmplugin WHERE username = ?");
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int limite = rs.getInt("limite");
                return limite;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static void setFarming(String player, boolean x) {
        checkConnection();
        try {
            PreparedStatement ps = MySQL.connection.prepareStatement("UPDATE farmplugin SET farming = ? WHERE username = ?");
            ps.setBoolean(1, x);
            ps.setString(2, player);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean getFarming(String player) {
        checkConnection();
        try {
            PreparedStatement ps = MySQL.connection.prepareStatement("SELECT farming FROM farmplugin WHERE username = ?");
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBoolean( "farming");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static FarmObject getPlayer(String player) {
        checkConnection();
        try {
            PreparedStatement ps = MySQL.connection.prepareStatement("SELECT * FROM farmplugin WHERE username = ?");
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new FarmObject(rs.getString("username"), rs.getInt("fungo_qtd"), rs.getInt("melon_qtd"), rs.getInt("sugar_qtd"), rs.getInt("blocos"), rs.getInt("limite"), rs.getBoolean("farming"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new FarmObject(null, 0,0,0,0,0, false);
    }

    public static void checkConnection() {
        try {
            if (MySQL.connection.isClosed() || MySQL.connection == null) {
                mysql.startConnection();
                Bukkit.getConsoleSender().sendMessage("A conexão foi estabelecida novamente!");
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("Erro ao checar a conexão!");
            e.printStackTrace();
        }
    }
}
