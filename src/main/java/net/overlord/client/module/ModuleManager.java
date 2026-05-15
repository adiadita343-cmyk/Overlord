package com.adiadita343;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    // Lista principală care stochează toate modulele
    public static List<Module> modules = new ArrayList<>();

    /**
     * Inițializează tot arsenalul Overlord.
     * Am adăugat manual module de elită și un generator pentru volum.
     */
    public static void init() {
        // --- CATEGORIA: COMBAT ---
        add("KillAura", "COMBAT");
        add("CrystalAura", "COMBAT");
        add("AutoTotem", "COMBAT");
        add("AnchorAura", "COMBAT");
        add("Criticals", "COMBAT");
        add("Velocity", "COMBAT");
        add("Reach", "COMBAT");
        add("AutoClicker", "COMBAT");
        add("BedAura", "COMBAT");
        add("BowAimbot", "COMBAT");
        add("FastBow", "COMBAT");

        // --- CATEGORIA: MOVEMENT ---
        add("Fly", "MOVEMENT");
        add("Speed", "MOVEMENT");
        add("Step", "MOVEMENT");
        add("Spider", "MOVEMENT");
        add("Jesus", "MOVEMENT");
        add("Sprint", "MOVEMENT");
        add("NoSlow", "MOVEMENT");
        add("SafeWalk", "MOVEMENT");
        add("EntitySpeed", "MOVEMENT");
        add("LongJump", "MOVEMENT");

        // --- CATEGORIA: VISUAL ---
        add("XRay", "VISUAL");
        add("Fullbright", "VISUAL");
        add("ESP", "VISUAL");
        add("Tracers", "VISUAL");
        add("Nametags", "VISUAL");
        add("StorageESP", "VISUAL");
        add("NoRender", "VISUAL");
        add("Search", "VISUAL");
        add("Breadcrumbs", "VISUAL");
        add("Trajectories", "VISUAL");

        // --- CATEGORIA: PLAYER ---
        add("NoFall", "PLAYER");
        add("AutoEat", "PLAYER");
        add("FastPlace", "PLAYER");
        add("ChestStealer", "PLAYER");
        add("AutoArmor", "PLAYER");
        add("InventoryWalk", "PLAYER");
        add("AutoTool", "PLAYER");
        add("Mucker", "PLAYER");

        // --- CATEGORIA: MISC ---
        add("Timer", "MISC");
        add("Freecam", "MISC");
        add("AutoReconnect", "MISC");
        add("AntiAfk", "MISC");
        add("MiddleClickFriend", "MISC");
        add("Spammer", "MISC");
        add("AutoSign", "MISC");

        // --- GENERATORUL DE 1200+ MODULE (Pentru volum imens) ---
        // Folosim nume profesionale pentru a simula un client masiv
        String[] prefixes = {"Hyper", "Ultra", "Apex", "Nova", "Flux", "Zenix", "Elite", "Prime", "Lava", "Frost"};
        String[] cats = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        
        for (int i = 1; i <= 1200; i++) {
            String category = cats[i % cats.length];
            String name = prefixes[i % prefixes.length] + category.substring(0, 1) + category.substring(1).toLowerCase() + "_" + i;
            add(name, category);
        }

        // Sortăm modulele alfabetic la pornire pentru un GUI ordonat
        modules.sort(Comparator.comparing(m -> m.name));
    }

    // Metodă internă pentru a adăuga rapid module
    private static void add(String name, String category) {
        modules.add(new Module(name, category));
    }

    // --- FUNCȚII AVANSATE (Ce face acest cod de 1 miliard de ori mai bun) ---

    /**
     * Returnează modulele active (pentru HUD-ul de tip Arraylist).
     */
    public static List<Module> getEnabledModules() {
        return modules.stream().filter(m -> m.enabled).collect(Collectors.toList());
    }

    /**
     * Filtrează modulele după o categorie specifică.
     */
    public static List<Module> getModulesInStatus(String category) {
        return modules.stream()
                .filter(m -> m.category.equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    /**
     * Motorul de căutare pentru Search Bar.
     */
    public static List<Module> getSearchQuery(String query) {
        if (query.isEmpty()) return modules;
        return modules.stream()
                .filter(m -> m.name.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Găsește un modul după nume (util pentru comenzi).
     */
    public static Module getModuleByName(String name) {
        return modules.stream()
                .filter(m -> m.name.equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    /**
     * Sortare avansată pentru Arraylist-ul din HUD.
     */
    public static void sortByWidth() {
        modules.sort((m1, m2) -> {
            // Aici s-ar putea adăuga lățimea textului din Minecraft
            return Integer.compare(m2.name.length(), m1.name.length());
        });
    }
}
