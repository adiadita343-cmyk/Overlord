package com.adiadita343.overlord.module;

public class PlayerRegistry {
    public static void register() {
        // === 🧠 Player Interaction & Mining ===
        ModuleManager.addModule(new Module("AutoMine", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("FastBreak", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("FastPlace", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoTool", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoUse", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoFish", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoFarm", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoTrade", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ChestStealer", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("InventoryManager", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("InventorySort", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("InventoryCleaner", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoCraft", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoSign", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ItemScroller", "PLAYER") { @Override public void onTick() {} });

        // === 🧍 Target Filter Logic Systems ===
        ModuleManager.addModule(new Module("TargetAssist", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("EnemyFilter", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("FriendSystem", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AntiBot", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("TeamCheck", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoTarget", "PLAYER") { @Override public void onTick() {} });

        // === 🧍 Player Utility & Quality of Life ===
        ModuleManager.addModule(new Module("AutoRespawn", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoReconnect", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoAFK", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AntiAFK", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoEat", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoDrink", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Timer", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("FakeLag", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("PingSpoof", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("LagSwitch", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("RotationSpoof", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("NoRotate", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("FastRespawn", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ClickGUI", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("HUDManager", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("NotificationSystem", "PLAYER") { @Override public void onTick() {} });

        // === 🧍 Advanced Internals & Exploits ===
        ModuleManager.addModule(new Module("MovementPrediction", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("HitPrediction", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("RotationEngine", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("PacketInterceptor", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("InputSpoof", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AntiDesync", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AntiKick", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Disabler", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("BypassLayer", "PLAYER") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutomationCore", "PLAYER") { @Override public void onTick() {} });
        
        // BONUS ADDED BY AI: FastEat (folosește pachete rapide pentru a mânca instant)
        ModuleManager.addModule(new Module("FastEat", "PLAYER") { @Override public void onTick() {} });
    }
}
