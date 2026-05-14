package com.adiadita343;

public class NumberSetting extends Setting {
    private double value;
    private double min, max, increment;

    public NumberSetting(String name, double defaultValue, double min, double max, double increment, Module parentMod) {
        super(name, parentMod);
        this.value = defaultValue;
        this.min = min;
        this.max = max;
        this.increment = increment;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        // Ne asigurăm că valoarea rămâne între minim și maxim
        double precision = 1.0 / increment;
        this.value = Math.round(Math.max(min, Math.min(max, value)) * precision) / precision;
    }

    public double getMin() { return min; }
    public double getMax() { return max; }
}
