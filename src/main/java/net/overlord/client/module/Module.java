package com.adiadita343;

import java.util.ArrayList;
import java.util.List;

public class Module {
    public String name;
    public String category;
    public boolean enabled;
    public List<Setting> settings = new ArrayList<>();

    public Module(String name, String category) {
        this.name = name;
        this.category = category;
        this.enabled = false;
    }

    public void addSetting(Setting setting) {
        this.settings.add(setting);
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }

    public void onTick() {}
}
