package net.overlord.client.module;

public class HackModule extends Module {
    
    public HackModule(String name, String category) {
        super(name, category);
    }

    @Override
    public void onTick() {
        // Aici poți pune logică generală dacă vrei, 
        // dar majoritatea logicii stă în Registries.
    }

    @Override
    public void onEnable() {
        System.out.println("[Overlord] " + this.name + " ACTIVAT!");
    }

    @Override
    public void onDisable() {
        System.out.println("[Overlord] " + this.name + " DEZACTIVAT!");
    }
}
