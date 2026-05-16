package com.adiadita343.overlord.module;

public class MovementRegistry {
    public static void register() {
        
        // Flight / Fly cu viteză și mod text configurabil
        ModuleManager.addModule(new Module("Flight", "MOVEMENT") {
            {
                this.value1 = 1.5;         // Viteza implicită de zbor
                this.mode = "VANILLA";     // Modul de funcționare
            }
            @Override public void onTick() {}
        });

        // Speed cu multiplicator de viteză pe sol
        ModuleManager.addModule(new Module("Speed", "MOVEMENT") {
            {
                this.value1 = 1.25;        // Valoarea multiplicatorului
                this.mode = "STRAFE";      // Tipul de mișcare aplicat
            }
            @Override public void onTick() {}
        });

        // Restul modulelor din categoria Movement scrise direct de mână
        ModuleManager.addModule(new Module("Fly", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("NoFall", "MOVEMENT") { { this.mode = "PACKET"; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Step", "MOVEMENT") { { this.value1 = 1.0; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("LongJump", "MOVEMENT") { { this.value1 = 3.0; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("HighJump", "MOVEMENT") { { this.value1 = 2.0; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Strafe", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("BunnyHop", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoSprint", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("NoSlow", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Jesus", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Scaffold", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Tower", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Blink", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("PacketFly", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ReverseStep", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AirJump", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("WebWalk", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Phase", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("LiquidWalk", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("YPort", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoWalk", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("FastLadder", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("InventoryMove", "MOVEMENT") { @Override public void onTick() {} });
    }
}
