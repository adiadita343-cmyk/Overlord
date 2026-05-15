package com.adiadita343;

import net.minecraft.client.MinecraftClient;

public class Module {
    public String name;
    public String category;
    public boolean enabled;
    public int keyCode = 0;
    protected static final MinecraftClient mc = MinecraftClient.getInstance();

    public Module(String name, String category) {
        this.name = name;
        this.category = category;
        this.enabled = false;
    }

    public void toggle() {
        this.enabled = !this.enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
}
