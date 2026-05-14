package com.adiadita343;

import net.minecraft.client.MinecraftClient;

public class Module {
    public String name;
    public String category;
    public boolean enabled = false;
    protected static MinecraftClient mc = MinecraftClient.getInstance();

    public Module(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public void toggle() {
        this.enabled = !this.enabled;
        if (enabled) onEnable(); else onDisable();
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {
        // Aici vine logica fiecărui modul
        if (enabled && mc.player != null) {
            doLogic();
        }
    }

    private void doLogic() {
        // Exemplu de logică automată pentru module de test
        if (name.contains("Fly")) mc.player.getAbilities().flying = true;
        if (name.contains("Speed")) mc.player.updateVelocity(0.2f, mc.player.getVelocity());
    }
}
