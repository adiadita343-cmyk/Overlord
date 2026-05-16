package net.overlord.com.adiadita343.module;

import net.minecraft.client.MinecraftClient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    public static final List<Module> modules = new ArrayList<>();
    public static final List<Module> activeModules = new ArrayList<>();
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void init() {
        modules.clear();
        CombatRegistry.register(); 
        MovementRegistry.register();
        VisualRegistry.register();
        PlayerRegistry.register();
        System.out.println("[Overlord] Module incarcate pentru pachetul adiadita343!");
    }

    public static void addModule(Module m) {
        modules.add(m);
    }

    public static void onTick() {
        if (mc.player == null) return;
        activeModules.clear();
        for (Module m : modules) {
            if (m.enabled) activeModules.add(m);
        }
        for (Module m : activeModules) {
            m.onTick();
        }
    }

    public static List<Module> getSearchQuery(String query) {
        if (query == null || query.isEmpty()) return modules;
        return modules.stream()
                .filter(m -> m.name.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static List<Module> getEnabledModules() {
        return modules.stream().filter(m -> m.enabled).collect(Collectors.toList());
    }
}
