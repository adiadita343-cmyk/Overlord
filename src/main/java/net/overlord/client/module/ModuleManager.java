package com.adiadita343;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    public static List<Module> modules = new ArrayList<>();

    public static void init() {
        // --- COMBAT (Elite) ---
        add("KillAura", "COMBAT");
        add("CrystalAura", "COMBAT");
        add("AutoTotem", "COMBAT");
        add("Reach", "COMBAT");
        add("Velocity", "COMBAT");

        // --- MOVEMENT (Elite) ---
        add("Fly", "MOVEMENT");
        add("Speed", "MOVEMENT");
        add("Step", "MOVEMENT");
        add("Sprint", "MOVEMENT");
        add("Jesus", "MOVEMENT");

        // --- VISUAL (Elite) ---
        add("XRay", "VISUAL");
        add("ESP", "VISUAL");
        add("Fullbright", "VISUAL");
        add("Tracer", "VISUAL");

        // --- GENERATOR 1100+ (Stil Meteor) ---
        String[] prefixes = {"Ultra", "Pro", "Advanced", "Mega", "Hyper", "Legit"};
        String[] cats = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        for (int i = 1; i <= 1100; i++) {
            String name = prefixes[i % prefixes.length] + "_" + i;
            add(name, cats[i % cats.length]);
        }
    }

    private static void add(String name, String cat) {
        modules.add(new Module(name, cat));
    }
}
