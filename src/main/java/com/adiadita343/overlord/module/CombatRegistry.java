package com.adiadita343.overlord.module;

public class CombatRegistry {
    public static void register() {
        // === ⚔️ Combat Essentials ===
        ModuleManager.addModule(new Module("AutoClicker", "COMBAT") { { this.value1 = 12.0; this.mode = "LEFT"; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("KillAura", "COMBAT") { { this.value1 = 4.2; this.mode = "SWITCH"; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("TriggerBot", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AimAssist", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("SilentAim", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Reach", "COMBAT") { { this.value1 = 5.0; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Velocity", "COMBAT") { { this.value1 = 0.0; this.value2 = 0.0; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AntiKnockback", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Criticals", "COMBAT") { { this.mode = "PACKET"; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Hitboxes", "COMBAT") { { this.value1 = 0.5; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoShield", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ShieldBreaker", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoWeapon", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoSwitch", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("TargetStrafe", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("MultiAura", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("TPAura", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("LegitAura", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("FastHit", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("NoSwing", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoAttack", "COMBAT") { @Override public void onTick() {} });

        // === 🛡️ AutoTotem / Survival ===
        ModuleManager.addModule(new Module("AutoTotem", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("TotemRefill", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("TotemSwap", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("InventoryTotem", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoOffhand", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoArmor", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ArmorSwap", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoGapple", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoHeal", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoPotion", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoSoup", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoPearl", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoCrystalDefense", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoElytraSwap", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("HealthManager", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("SafetyModule", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoLeave", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoDisconnect", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AntiVoid", "COMBAT") { @Override public void onTick() {} });

        // === 💥 Crystal PvP Core ===
        ModuleManager.addModule(new Module("AutoCrystal", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalAura", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalPlace", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalBreak", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalAssist", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalOptimizer", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalPredict", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalDelaySync", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalRange", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalHitbox", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalDamageCalc", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalTarget", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalSwitch", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalFastPlace", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalFastBreak", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalLagFix", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalAntiLagBack", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalTiming", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalMultiPlace", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalMultiBreak", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalAnchorSync", "COMBAT") { @Override public void onTick() {} });

        // === 🪨 Anchor PvP Modules ===
        ModuleManager.addModule(new Module("AnchorAura", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorAssist", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorPlace", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorBreak", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorPredict", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorDelay", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorRange", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorSwap", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorTarget", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorFastPlace", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorFastBreak", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorOptimizer", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorSync", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorAutoTotem", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorAntiDesync", "COMBAT") { @Override public void onTick() {} });

        // === 🧠 CPvP Utility Systems ===
        ModuleManager.addModule(new Module("HoleFill", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Surround", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("SelfTrap", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoTrap", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CityBreaker", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AntiCity", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Burrow", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("WebTrap", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoWeb", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AntiCrystal", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AntiAnchor", "COMBAT") { @Override public void onTick() {} });
        
        // Extra utile adăugate de mine:
        ModuleManager.addModule(new Module("ObsidianExtender", "COMBAT") { @Override public void onTick() {} });
    }
}
