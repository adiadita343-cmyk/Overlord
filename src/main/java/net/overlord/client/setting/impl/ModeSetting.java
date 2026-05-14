package net.overlord.client.setting.impl;

import net.overlord.client.setting.Setting;
import java.util.Arrays;
import java.util.List;

public class ModeSetting extends Setting<String> {
    private final List<String> modes;
    private int index;

    public ModeSetting(String name, String description, String defaultValue, String... modes) {
        super(name, description, defaultValue);
        this.modes = Arrays.asList(modes);
        this.index = this.modes.indexOf(defaultValue);
    }

    public List<String> getModes() {
        return modes;
    }

    public void cycle() {
        index++;
        if (index >= modes.size()) index = 0;
        this.setValue(modes.get(index));
    }
}