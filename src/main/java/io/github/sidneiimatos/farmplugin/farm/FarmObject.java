package io.github.sidneiimatos.farmplugin.farm;

import io.github.sidneiimatos.farmplugin.FarmPlugin;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class FarmObject {
    private String player;
    private int fungo;
    private int melon;
    private int sugar;
    private int blocos;
    private int limite;
    private boolean farming;

    public FarmObject() {
    }

    public FarmObject(String player, int fungo, int melon, int sugar, int blocos, int limite, boolean farming) {
        this.player = player;
        this.fungo = fungo;
        this.melon = melon;
        this.sugar = sugar;
        this.blocos = blocos;
        this.limite = limite;
        this.farming = farming;
    }

    public boolean isFarming() {
        return farming;
    }

    public void setFarming(boolean farming) {
        this.farming = farming;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getFungo() {
        return fungo;
    }

    public void setFungo(int fungo) {
        this.fungo = fungo;
    }

    public int getMelon() {
        return melon;
    }

    public void setMelon(int melon) {
        this.melon = melon;
    }
    public void addDrops(String type, int drop) {
        if (type.equals("melon")) {
            this.melon += drop;
        } else if (type.equals("sugar")) {
            this.sugar += drop;
        } else if (type.equals("fungo")) {
            this.fungo += drop;
        }
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getBlocos() {
        return blocos;
    }

    public void setBlocos(int blocos) {
        this.blocos += blocos;
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }
}
