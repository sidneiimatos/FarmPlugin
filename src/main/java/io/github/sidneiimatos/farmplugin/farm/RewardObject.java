package io.github.sidneiimatos.farmplugin.farm;

import io.github.sidneiimatos.farmplugin.FarmPlugin;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class RewardObject {
    private String nome = "";
    private String title = "";
    private String sub_title = "";
    private int position;

    //CRIAR VARIAVEIS QUE VÃO GUARDAR OS COMANDOS PARA GIVAR AO PLAYER SUA RECOMPENSA


    public RewardObject(String nome) {
        this.nome = nome;
    }

    public RewardObject(String nome, String title, String sub_title, int position) {
        this.nome = nome;
        this.title = title;
        this.sub_title = sub_title;
        this.position = position;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
