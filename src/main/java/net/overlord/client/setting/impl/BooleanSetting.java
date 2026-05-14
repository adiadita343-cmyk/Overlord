package com.adiadita343;

public class BooleanSetting extends Setting {
    private boolean enabled;

    public BooleanSetting(String name, boolean enabled, Module parentMod) {
        super(name, parentMod);
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }
}
