package com.adiadita343;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    public static List<Module> modules = new ArrayList<>();
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void init() {
        // --- MODULE ELITE ---
        add("KillAura", "COMBAT");
        add("Flight", "MOVEMENT");
        add("Speed", "MOVEMENT");
        add("NoFall", "PLAYER");
        add("Fullbright", "VISUAL");
        add("Spider", "MOVEMENT");

        // --- GENERATOR MASIV (Mii de hack-uri) ---
        String[] hacks = {"Aimbot", "Blink", "Phase", "Step", "Jesus", "Nuker", "Scaffold", "XRay", "ESP", "Tracers", "Nametags", "FastPlace", "AutoEat"};
        String[] cats = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        
        for (int i = 0; i < 2000; i++) {
            add(hacks[i % hacks.length] + "_" + (i + 1), cats[i % cats.length]);
        }
    }

    private static void add(String name, String cat) {
        modules.add(new Module(name, cat));
    }

    public static void onTick() {
        if (mc.player == null) return;
        for (Module m : modules) {
            if (m.enabled) {
                m.onTick();
                
                // LOGICA KILLAURA
                if (m.name.equals("KillAura")) {
                    for (Entity target : mc.world.getEntities()) {
                        if (target instanceof PlayerEntity && target != mc.player && mc.player.distanceTo(target) < 4.2) {
                            mc.interactionManager.attackEntity(mc.player, target);
                            mc.player.swingHand(mc.player.getActiveHand());
                            break;
                        }
                    }
                }
                
                // LOGICA FLIGHT
                if (m.name.equals("Flight")) {
                    mc.player.getAbilities().flying = true;
                }
                
                // LOGICA FULLBRIGHT
                if (m.name.equals("Fullbright")) {
                    mc.options.getGamma().setValue(100.0);
                }
            } else {
                // RESETĂRI
                if (m.name.equals("Flight") && !mc.player.isCreative()) {
                    mc.player.getAbilities().flying = false;
                }
                if (m.name.equals("Fullbright")) {
                    mc.options.getGamma().setValue(1.0);
                }
            }
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
