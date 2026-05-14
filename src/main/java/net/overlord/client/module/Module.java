package com.adiadita343;

import net.minecraft.client.MinecraftClient;
import java.util.ArrayList;
import java.util.List;

public class Module {
    public String name;
    public String category;
    public boolean enabled = false;
    // Această listă va ține toate setările (Boolean, Number, Mode)
    public List<Setting> settings = new ArrayList<>();
    protected static MinecraftClient mc = MinecraftClient.getInstance();

    public Module(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public void addSetting(Setting setting) {
        this.settings.add(setting);
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }

    // Aici va veni logica de hack (ex: killaura, fly)
    public void onTick() {
    }
}
