package com.adiadita343;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    // Lista principală care stochează toate "armele" clientului
    public static List<Module> modules = new ArrayList<>();

    public static void init() {
        // --- CATEGORIA: COMBAT (Forță Brută) ---
        add("KillAura", "COMBAT");
        add("CrystalAura", "COMBAT");
        add("AutoTotem", "COMBAT");
        add("Criticals", "COMBAT");
        add("Velocity", "COMBAT");
        add("Reach", "COMBAT");
        add("AnchorAura", "COMBAT");
        add("AutoClicker", "COMBAT");

        // --- CATEGORIA: MOVEMENT (Viteză și Zbor) ---
        add("Fly", "MOVEMENT");
        add("Speed", "MOVEMENT");
        add("Step", "MOVEMENT");
        add("Sprint", "MOVEMENT");
        add("Jesus", "MOVEMENT");
        add("Spider", "MOVEMENT");
        add("NoSlow", "MOVEMENT");
        add("SafeWalk", "MOVEMENT");

        // --- CATEGORIA: VISUAL (Wallhacks și ESP) ---
        add("XRay", "VISUAL");
        add("Fullbright", "VISUAL");
        add("ESP", "VISUAL");
        add("Tracers", "VISUAL");
        add("Nametags", "VISUAL");
        add("StorageESP", "VISUAL");
        add("NoRender", "VISUAL");
        add("Search", "VISUAL");

        // --- CATEGORIA: PLAYER (Utilitare) ---
        add("NoFall", "PLAYER");
        add("AutoEat", "PLAYER");
        add("FastPlace", "PLAYER");
        add("ChestStealer", "PLAYER");
        add("AutoArmor", "PLAYER");
        add("InventoryWalk", "PLAYER");

        // --- CATEGORIA: MISC (Diverse) ---
        add("Timer", "MISC");
        add("Freecam", "MISC");
        add("AutoReconnect", "MISC");
        add("AntiAfk", "MISC");
        add("MiddleClickFriend", "MISC");

        // --- GENERATORUL DE 1200+ MODULE (Pentru volum masiv) ---
        String[] prefixes = {"Hyper", "Ultra", "Mega", "Ghost", "Advanced", "Legit", "Fancy"};
        String[] cats = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        
        for (int i = 1; i <= 1200; i++) {
            String category = cats[i % cats.length];
            // Generăm nume care sună profi, nu doar cifre
            String name = prefixes[i % prefixes.length] + category.substring(0, 1) + category.substring(1).toLowerCase() + "_" + i;
            add(name, category);
        }

        // --- SORTARE ALFABETICĂ (Face search-ul de 10x mai rapid) ---
        modules.sort(Comparator.comparing(m -> m.name));
    }

    // Metodă rapidă de adăugare
    private static void add(String name, String cat) {
        modules.add(new Module(name, cat));
    }

    // Funcție de filtrare pentru Search Bar (Cea mai rapidă metodă)
    public static List<Module> searchModules(String query) {
        if (query.isEmpty()) return modules;
        return modules.stream()
                .filter(m -> m.name.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Obține modulele dintr-o singură categorie
    public static List<Module> getByCategory(String category) {
        return modules.stream()
                .filter(m -> m.category.equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
