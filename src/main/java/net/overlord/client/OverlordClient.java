package com.adiadita343;

import net.fabricmc.api.ClientModInitializer;
import java.util.ArrayList;
import java.util.List;

public class OverlordClient implements ClientModInitializer {
    // Această listă va stoca toate setările pentru toate cele 1200 de module
    public static List<Setting> settings = new ArrayList<>();
    public static OverlordClient instance;

    @Override
    public void onInitializeClient() {
        instance = this;
        
        // Aici poți adăuga logica de salvare/încărcare a setărilor în viitor
        System.out.println("Overlord Client Side Initialized!");
    }

    // Metodă utilă pentru a găsi setările unui anumit modul
    public static List<Setting> getSettingsForModule(Module module) {
        List<Setting> moduleSettings = new ArrayList<>();
        for (Setting s : settings) {
            // Presupunem că Setting.java are o referință către modul
            if (s.getParentMod() == module) {
                moduleSettings.add(s);
            }
        }
        return moduleSettings;
    }
}
