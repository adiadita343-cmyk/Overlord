package net.overlord.adiadita343.module;

import net.minecraft.client.MinecraftClient;

public abstract class Module {
    public String name;
    public String category;
    public boolean enabled;
    public int keyCode;

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
    public abstract void onTick();
}
