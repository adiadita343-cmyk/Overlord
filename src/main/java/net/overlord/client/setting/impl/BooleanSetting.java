package net.overlord.client.setting.impl;

import net.overlord.client.setting.Setting;

public class BooleanSetting extends Setting<Boolean> {

    public BooleanSetting(String name, String description, boolean defaultValue) {
        super(name, description, defaultValue);
    }

    public void toggle() {
        this.setValue(!this.getValue());
    }
}