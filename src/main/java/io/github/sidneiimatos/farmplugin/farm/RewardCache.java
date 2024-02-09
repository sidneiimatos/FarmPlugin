package io.github.sidneiimatos.farmplugin.farm;

import java.util.HashMap;

public class RewardCache {
    private HashMap<String, RewardObject> rewards = new HashMap<>();


    public RewardObject getReward(String nome) {
        if (rewards.containsKey(nome)) {
            return rewards.get(nome);
        }
        return null;
    }

    public HashMap<String, RewardObject> getCache() {
        return this.rewards;
    }
}
