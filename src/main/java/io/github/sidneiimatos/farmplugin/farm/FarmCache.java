package io.github.sidneiimatos.farmplugin.farm;

import java.util.HashMap;

public class FarmCache {
    private HashMap<String, FarmObject> cache = new HashMap<>();

    public FarmObject getPlayer(String player) {
        if (this.cache.containsKey(player)) {
            return cache.get(player);
        }
        return null;
    }

    public HashMap<String, FarmObject> getCache() {
        return this.cache;
    }
}
