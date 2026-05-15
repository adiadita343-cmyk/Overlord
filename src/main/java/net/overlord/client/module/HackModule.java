package com.adiadita343;

// HackModule "moștenește" tot ce are Module
public class HackModule extends Module {

    public HackModule(String name, String category) {
        super(name, category); // Trimite numele și categoria către fișierul Module.java
    }

    @Override
    public void toggle() {
        super.toggle(); // Face toggle-ul normal
        
        // Adăugăm ceva extra: un mesaj în consolă/log-uri
        if (this.enabled) {
            System.out.println("[Overlord] " + this.name + " a fost ACTIVAT!");
            onEnable();
        } else {
            System.out.println("[Overlord] " + this.name + " a fost DEZACTIVAT!");
            onDisable();
        }
    }

    public void onEnable() {
        // Aici vei pune cod special pentru când pornești hack-ul
    }

    public void onDisable() {
        // Aici vei pune cod special pentru când îl oprești
    }
}
