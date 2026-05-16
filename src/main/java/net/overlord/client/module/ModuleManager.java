package com.adiadita343.module;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    public static final List<Module> modules = new ArrayList<>();
    
    // Optimizare stil Meteor: Procesăm doar ce e activ
    public static final List<Module> activeModules = new ArrayList<>();

    public static void init() {
        modules.clear();
        
        // Aici injectăm manual TOATE listele tale
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
        if (mc.player == null) return;

        // Actualizăm lista de module pornite (cache)
        activeModules.clear();
        for (Module m : modules) {
            if (m.enabled) activeModules.add(m);
        }

        // Executăm logica DOAR pentru modulele pornite
        for (Module m : activeModules) {
            m.onTick();
        }
    }
}
