package io.github.sidneiimatos.farmplugin.farm;

public class ToolObject {

    private String player;
    private double eficiency;
    private double fortune;
    private double pressa;
    private double sortudo;


    public ToolObject(String player, double eficiency, double fortune, double pressa, double sortudo) {
        this.player = player;
        this.eficiency = eficiency;
        this.fortune = fortune;
        this.pressa = pressa;
        this.sortudo = sortudo;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public double getEficiency() {
        return eficiency;
    }

    public void setEficiency(int eficiency) {
        this.eficiency += eficiency;
    }

    public double getFortune() {
        return fortune;
    }

    public void setFortune(int fortune) {
        this.fortune += fortune;
    }

    public double getPressa() {
        return pressa;
    }

    public void setPressa(int pressa) {
        this.pressa += pressa;
    }

    public double getSortudo() {
        return sortudo;
    }

    public void setSortudo(int sortudo) {
        this.sortudo += sortudo;
    }
}
