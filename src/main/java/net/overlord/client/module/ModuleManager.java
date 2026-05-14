package com.adiadita343;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    public static List<Module> modules = new ArrayList<>();

    public static void init() {
        modules.clear();
        String[] cats = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        for (String c : cats) {
            for (int i = 1; i <= 240; i++) {
                Module m = new Module(c + "_" + i, c);
                m.addSetting(new BooleanSetting("Active", true, m));
                modules.add(m);
            }
        }
    }

    public static List<Module> getModulesByCategory(String category) {
        return modules.stream()
                .filter(m -> m.category.equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
