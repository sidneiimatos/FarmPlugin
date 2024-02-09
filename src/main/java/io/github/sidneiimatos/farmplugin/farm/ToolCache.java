package io.github.sidneiimatos.farmplugin.farm;

import javax.tools.Tool;
import java.util.HashMap;

public class ToolCache {
    private HashMap<String, ToolObject> tool = new HashMap<>();


    public ToolObject getPick(String nome) {
        if (tool.containsKey(nome)) {
            return tool.get(nome);
        }
        return null;
    }

    public HashMap<String, ToolObject> getCache() {
        return this.tool;
    }
}
