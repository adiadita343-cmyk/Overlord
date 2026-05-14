package net.overlord.client.module;

public abstract class HackModule {
    private String name;
    private Category category;
    private boolean enabled;

    public HackModule(String name, Category category) {
        this.name = name;
        this.category = category;
        this.enabled = false;
    }

    public enum Category {
        COMBAT, MOVEMENT, RENDER, PLAYER, MISC
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
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