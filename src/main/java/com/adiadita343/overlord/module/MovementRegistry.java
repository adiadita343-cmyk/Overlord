package com.adiadita343.overlord.module;

public class MovementRegistry {
    public static void register() {
        // === 👁️ Movement Essentials ===
        ModuleManager.addModule(new Module("Flight", "MOVEMENT") { { this.value1 = 1.5; this.mode = "VANILLA"; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Fly", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Speed", "MOVEMENT") { { this.value1 = 1.25; this.mode = "STRAFE"; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("NoFall", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Step", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("LongJump", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("HighJump", "MOVEMENT") { @Override public void onTick() {} });
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

        // === 🧩 Advanced Systems & Spoofs ===
        ModuleManager.addModule(new Module("Glide", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("SafeWalk", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("FastSwim", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("InventoryMove", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ElytraBoost", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("EntitySpeed", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("FastLadder", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("NoSlowdown", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("IceSpeed", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Parkour", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("BoatFly", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("BlockLag", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("NoClip", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("VClip", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("HClip", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("FastWeb", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Sneak", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("InfiniteJump", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("WallClimb", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("EdgeJump", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("SmoothWalk", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoJump", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ClimbAssist", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ElytraFly", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ElytraControl", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("EntityControl", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("MovementSpoof", "MOVEMENT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("TickShift", "MOVEMENT") { @Override public void onTick() {} });
    }
}
