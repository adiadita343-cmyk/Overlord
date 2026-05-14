package com.adiadita343;

import net.minecraft.client.MinecraftClient;
import java.util.ArrayList;
import java.util.List;

public class HackModule {
    public String name;
    public String category;
    public boolean enabled;
    public List<Setting> settings = new ArrayList<>();
    protected static MinecraftClient mc = MinecraftClient.getInstance();

    public HackModule(String name, String category) {
        this.name = name;
        this.category = category;
        this.enabled = false;
    }

    public void addSetting(Setting setting) {
        this.settings.add(setting);
    }

    public void toggle() {
        this.enabled = !this.enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
}
