package net.overlord.client.setting;
public abstract class Setting<T> {
    private final String name;
    private final String description;
    protected T value;

    public Setting(String name, String description, T value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public T getValue() { return value; }
    public void setValue(T value) { this.value = value; }
}