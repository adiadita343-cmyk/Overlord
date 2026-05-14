package com.adiadita343;

import net.minecraft.client.MinecraftClient;
import java.util.ArrayList;
import java.util.List;

public class Module {
    public String name;
    public String category;
    public boolean enabled = false;
    public boolean opened = false; // Pentru afișare în GUI
    public List<BooleanSetting> settings = new ArrayList<>();
    protected static MinecraftClient mc = MinecraftClient.getInstance();

    public Module(String name, String category) {
        this.name = name;
        this.category = category;
        // Adăugăm o setare default de test pentru fiecare modul
        this.settings.add(new BooleanSetting("Active", true));
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }

    public void onTick() {
        if (enabled && mc.player != null) {
            // Aici poți adăuga logică specifică
        }
    }
}

class BooleanSetting {
    public String name;
    public boolean enabled;

    public BooleanSetting(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }
}
