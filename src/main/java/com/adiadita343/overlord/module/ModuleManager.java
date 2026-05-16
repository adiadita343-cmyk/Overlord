package com.adiadita343.overlord.module;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    // Lista globală în care sunt salvate toate modulele din client
    public static final List<Module> modules = new ArrayList<>();

    // Metoda care pornește și înregistrează toate categoriile de module
    public static void init() {
        modules.clear();

        // Încărcăm registrele pe care le-am configurat pe pachetul com.adiadita343
        CombatRegistry.register();
        MovementRegistry.register();
        VisualRegistry.register();
        PlayerRegistry.register();

        System.out.println("[Overlord] ModuleManager a pornit cu succes pe noua structura!");
    }

    // Adaugă un modul nou în listă
    public static void addModule(Module module) {
        modules.add(module);
    }

    // Rulează logica din module la fiecare tick, doar dacă jucătorul este intrat pe o lume/server
    public static void onTick() {
        if (Module.mc.player == null) return;
        
        for (Module m : modules) {
            if (m.enabled) {
                m.onTick();
            }
        }
    }

    // Funcție folosită de căsuța de Search din ClickGUI-ul tău mov pentru a filtra modulele după text
    public static List<Module> getSearchQuery(String query) {
        if (query == null || query.isEmpty()) return modules;
        return modules.stream()
                .filter(m -> m.name.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Returnează o listă doar cu modulele activate în acel moment
    public static List<Module> getEnabledModules() {
        return modules.stream()
                .filter(m -> m.enabled)
                .collect(Collectors.toList());
    }
}
