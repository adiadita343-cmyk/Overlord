package com.adiadita343;

public class Setting {
    private String name;
    private Module parentMod;
    private boolean visible = true;

    public Setting(String name, Module parentMod) {
        this.name = name;
        this.parentMod = parentMod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Module getParentMod() {
        return parentMod;
    }

    public void setParentMod(Module parentMod) {
        this.parentMod = parentMod;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
