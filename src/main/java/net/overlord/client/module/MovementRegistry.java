package net.overlord.com.adiadita343.module;

public class MovementRegistry {
    public static void register() {
        ModuleManager.addModule(new Module("Step", "MOVEMENT") {
            @Override
            public void onTick() {
                // Fix pentru eroarea de compilare: folosim variabila, nu metoda
                mc.player.stepHeight = 2.0f;
            }
            @Override
            public void onDisable() {
                mc.player.stepHeight = 0.6f;
            }
        });

        // Restul de module...
        String[] extra = {"Fly", "Speed", "NoFall", "Sprint"};
        for (String s : extra) {
            ModuleManager.addModule(new Module(s, "MOVEMENT") {
                @Override public void onTick() {}
            });
        }
    }
}
