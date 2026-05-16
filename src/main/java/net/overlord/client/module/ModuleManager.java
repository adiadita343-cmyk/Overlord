package com.adiadita343.module;

import net.minecraft.client.MinecraftClient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    public static final List<Module> modules = new ArrayList<>();
    public static final List<Module> activeModules = new ArrayList<>();
    
    // ACEASTA LINIE LIPSEA: Definirea clientului Minecraft
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void init() {
        modules.clear();
        
        // Înregistrăm toate cele 2500+ de module din registrele noastre
        CombatRegistry.register(); 
        MovementRegistry.register();
        VisualRegistry.register();
        PlayerRegistry.register();

        System.out.println("[Overlord] 2500+ Module încărcate manual.");
    }

    public static void addModule(Module m) {
        modules.add(m);
    }

    public static void onTick() {
        // Acum programul știe ce e 'mc'
        if (mc.player == null) return;

        // Resetăm și umplem lista de module active
        activeModules.clear();
        for (Module m : modules) {
            if (m.enabled) activeModules.add(m);
        }

        // Executăm logica doar pentru cele pornite
        for (Module m : activeModules) {
            m.onTick();
        }
    }

    // Adăugăm și funcția de căutare pentru ClickGUI
    public static List<Module> getSearchQuery(String query) {
        if (query == null || query.isEmpty()) return modules;
        return modules.stream()
                .filter(m -> m.name.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
