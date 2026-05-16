package com.adiadita343.overlord.module;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    // Lista globală în care Meteor/Overlord salvează instanțele tuturor modulelor
    public static final List<Module> modules = new ArrayList<>();

    // Metoda de inițializare apelată la pornirea jocului (în initializer-ul tău principal)
    public static void init() {
        modules.clear();

        // Încărcăm pe rând cele 4 registre care conțin TOATE cele 450+ de module din listă
        CombatRegistry.register();
        MovementRegistry.register();
        VisualRegistry.register();
        PlayerRegistry.register();

        System.out.println("[Overlord] Meteor-Style ModuleManager a pornit! Toate cele 450+ de module sunt incarcate.");
    }

    // Funcție folosită de registre pentru a adăuga modulele în memoria RAM a jocului
    public static void addModule(Module module) {
        modules.add(module);
    }

    // Rutină secundară de tick (pentru compatibilitate sau logici globale de siguranță)
    public static void onTick() {
        if (Module.mc.player == null) return;
        
        // În sistemul EventBus, modulele își rulează logica singure prin @EventHandler,
        // dar păstrăm asta aici dacă vrei să adaugi verificări globale mai târziu.
    }

    // Funcție vitală folosită de căsuța de Search din meniul tău mov pentru a filtra modulele instant în timp ce tastezi
    public static List<Module> getSearchQuery(String query) {
        if (query == null || query.isEmpty()) return modules;
        return modules.stream()
                .filter(m -> m.name.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Returnează o listă curată doar cu modulele pe care le-ai activat (folositoare pentru HUD / ArrayList pe ecran)
    public static List<Module> getEnabledModules() {
        return modules.stream()
                .filter(m -> m.enabled)
                .collect(Collectors.toList());
    }
}
