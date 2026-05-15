package com.adiadita343;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    public static List<Module> modules = new ArrayList<>();

    public static void init() {
        // --- MODULE REALE (Scriem logica lor mai jos sau în clase separate) ---
        add("KillAura", "COMBAT");
        add("AutoTotem", "COMBAT");
        add("Flight", "MOVEMENT");
        add("XRay", "VISUAL");
        add("Fullbright", "VISUAL");
        add("Speed", "MOVEMENT");

        // --- GENERATOR MASIV (Kilolitri de nume stil Meteor/Future) ---
        String[] prefixes = {"Hyper", "Nova", "Astro", "Matrix", "Void", "Cyber", "Fatal", "Logic", "Zenith", "Omega", "Vortex"};
        String[] suffixes = {"Aura", "Fly", "Speed", "ESP", "Bypass", "Plus", "Ultra", "Shift", "Glide", "Reach"};
        String[] cats = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};

        for (int i = 0; i < 2000; i++) { // Am pus 2000 pentru performanță, dar poți mări
            String name = prefixes[i % prefixes.length] + suffixes[(i / prefixes.length) % suffixes.length] + "_" + i;
            add(name, cats[i % cats.length]);
        }
    }

    private static void add(String name, String cat) {
        modules.add(new Module(name, cat));
    }

    // !!! ASTA E CEA MAI IMPORTANTĂ METODĂ !!!
    // Trebuie chemată din clasa ta principală (ex. Overlord.java) la fiecare tick de joc!
    public static void onTick() {
        if (MinecraftClient.getInstance().player == null) return;
        
        for (Module m : modules) {
            if (m.enabled) {
                m.onTick();
                
                // --- LOGICĂ HARDCODED PENTRU TEST (Să vezi că merge!) ---
                if (m.name.equals("Flight")) {
                    MinecraftClient.getInstance().player.getAbilities().flying = true;
                }
                if (m.name.equals("Fullbright")) {
                    MinecraftClient.getInstance().options.getGamma().setValue(100.0);
                }
            } else {
                // Ce se întâmplă când dezactivezi
                if (m.name.equals("Flight") && !MinecraftClient.getInstance().player.isCreative()) {
                    MinecraftClient.getInstance().player.getAbilities().flying = false;
                }
            }
        }
    }
}
