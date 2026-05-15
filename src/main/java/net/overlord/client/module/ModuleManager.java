package com.adiadita343;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    // Lista principală unde stocăm toate cele 1250 de module
    public static List<Module> modules = new ArrayList<>();

    public static void init() {
        modules.clear();
        
        // Definim categoriile standard pentru un client de hack-uri
        String[] categories = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        
        for (String cat : categories) {
            for (int i = 1; i <= 250; i++) {
                // Generăm module cu nume unic, ex: COMBAT_1, COMBAT_2...
                // Folosim constructorul simplu (Nume, Categorie)
                modules.add(new Module(cat + "_" + i, cat));
            }
        }
        
        System.out.println("Overlord Client: S-au incarcat " + modules.size() + " module.");
    }

    // Funcție care ne ajută să filtrăm modulele pentru ClickGUI
    public static List<Module> getModulesByCategory(String category) {
        return modules.stream()
                .filter(m -> m.category.equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
