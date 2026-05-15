package com.adiadita343;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    public static List<Module> modules = new ArrayList<>();

    public static void init() {
        modules.clear();
        // Categoriile tale de hack-uri
        String[] cats = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        
        for (String c : cats) {
            for (int i = 1; i <= 250; i++) {
                // Generăm module cu nume unic: COMBAT_1, COMBAT_2... până la 250
                modules.add(new Module(c + "_" + i, c));
            }
        }
    }

    public static List<Module> getModulesByCategory(String category) {
        return modules.stream()
                .filter(m -> m.category.equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
