package com.adiadita343;

import java.util.ArrayList;
import java.util.List;

public class Module {
    public String name;
    public String category;
    public boolean enabled;
    // Lista de setări (pentru BooleanSetting, NumberSetting etc.)
    public List<Setting> settings = new ArrayList<>();

    // Constructorul principal pe care îl folosește ModuleManager
    public Module(String name, String category) {
        this.name = name;
        this.category = category;
        this.enabled = false; // Toate modulele pornesc dezactivate
    }

    // Metodă pentru a adăuga setări ulterior
    public void addSetting(Setting setting) {
        this.settings.add(setting);
    }

    // Metodă pentru activare/dezactivare
    public void toggle() {
        this.enabled = !this.enabled;
    }

    // Aici se va scrie logica pentru fiecare hack (se apelează în fiecare frame)
    public void onTick() {
    }
}
