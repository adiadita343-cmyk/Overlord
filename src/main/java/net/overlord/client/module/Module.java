package net.overlord.com.adiadita343.module;

import net.minecraft.client.MinecraftClient;

public abstract class Module {
    // Instanța supremă de Minecraft accesibilă de peste tot
    public static final MinecraftClient mc = MinecraftClient.getInstance();

    public String name;
    public String category;
    public boolean enabled;
    public int keyCode; // Păstrat special pentru ClickGUI-ul tău mov

    public Module(String name, String category) {
        this.name = name;
        this.category = category;
        this.enabled = false;
    }

    public void onEnable() {}
    public void onDisable() {}
    
    // Metodele noi de evenimente (Sistemul Meteor-Style)
    public void onTick() {}
    public void onRender() {}

    public void toggle() {
        this.enabled = !this.enabled;
        if (enabled) onEnable();
        else onDisable();
    }
}
