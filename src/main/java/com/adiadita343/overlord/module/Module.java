package com.adiadita343.overlord.module;

import net.minecraft.client.MinecraftClient;

public abstract class Module {
    public static final MinecraftClient mc = MinecraftClient.getInstance();

    public String name;
    public String category;
    public boolean enabled;
    public int keyCode;

    public Module(String name, String category) {
        this.name = name;
        this.category = category;
        this.enabled = false;
    }

    public void onEnable() {}
    public void onDisable() {}
    public abstract void onTick();
    public void onRender() {}

    public void toggle() {
        this.enabled = !this.enabled;
        if (enabled) onEnable();
        else onDisable();
    }
}
