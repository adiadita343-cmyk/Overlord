package net.overlord.client.module;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    // Lista de module trebuie să fie statică pentru a fi accesată din ClickGuiScreen
    public static List<HackModule> modules = new ArrayList<>();

    public ModuleManager() {
        if (modules.isEmpty()) {
            // Generăm module de test pentru fiecare categorie
            for (HackModule.Category category : HackModule.Category.values()) {
                for (int i = 1; i <= 5; i++) {
                    String name = category.name() + " Mod " + i;
                    modules.add(new HackModule(name, category) {});
                }
            }
        }
    }

    public static List<HackModule> getModules() {
        return modules;
    }
}