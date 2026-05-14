package com.adiadita343;

import java.util.Arrays;
import java.util.List;

public class ModeSetting extends Setting {
    private int index;
    private List<String> modes;

    public ModeSetting(String name, String defaultMode, Module parentMod, String... modes) {
        super(name, parentMod);
        this.modes = Arrays.asList(modes);
        this.index = this.modes.indexOf(defaultMode);
    }

    public String getMode() {
        return modes.get(index);
    }

    public void cycle() {
        // Trece la următoarea opțiune din listă, iar după ultima revine la prima
        if (index < modes.size() - 1) {
            index++;
        } else {
            index = 0;
        }
    }

    public List<String> getModes() {
        return modes;
    }
}
