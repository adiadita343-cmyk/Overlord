package com.adiadita343.overlord.module;

public class CombatRegistry {
    public static void register() {

        // ==========================================
        // 1. COMBAT ESSENTIALS (CU SETĂRI INTEGRATE)
        // ==========================================
        
        // AutoClicker cu CPS și setare de mouse (LEFT/RIGHT)
        ModuleManager.addModule(new Module("AutoClicker", "COMBAT") {
            {
                this.value1 = 12.0;    // Viteza implicită: 12 CPS
                this.mode = "LEFT";    // Poate fi schimbat în "RIGHT" din meniu
            }
            @Override public void onTick() {}
        });

        // KillAura cu rază de acțiune și mod de țintire
        ModuleManager.addModule(new Module("KillAura", "COMBAT") {
            {
                this.value1 = 4.2;      // Raza de atac implicită: 4.2 blocuri
                this.mode = "SWITCH";   // Modul de alternare a țintelor
            }
            @Override public void onTick() {}
        });

        // Reach configurabil pentru distanța de atingere
        ModuleManager.addModule(new Module("Reach", "COMBAT") {
            {
                this.value1 = 5.0;      // Setează raza maximă la 5.0 blocuri
            }
            @Override public void onTick() {}
        });

        // Velocity pentru modificarea reculului (Knockback)
        ModuleManager.addModule(new Module("Velocity", "COMBAT") {
            {
                this.value1 = 0.0;      // 0% Knockback pe orizontală
                this.value2 = 0.0;      // 0% Knockback pe verticală
            }
            @Override public void onTick() {}
        });

        // Restul modulelor din categoria Combat Essentials
        ModuleManager.addModule(new Module("TriggerBot", "COMBAT") { { this.value1 = 14.0; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AimAssist", "COMBAT") { { this.value1 = 3.0; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("SilentAim", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AntiKnockback", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Criticals", "COMBAT") { { this.mode = "PACKET"; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Hitboxes", "COMBAT") { { this.value1 = 0.5; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoShield", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ShieldBreaker", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoWeapon", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoSwitch", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("TargetStrafe", "COMBAT") { { this.value1 = 2.5; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("MultiAura", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("TPAura", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("LegitAura", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("FastHit", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("NoSwing", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoAttack", "COMBAT") { @Override public void onTick() {} });

        // ==========================================
        // 2. SURVIVAL & SAFETY MODULES
        // ==========================================
        ModuleManager.addModule(new Module("AutoTotem", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("TotemRefill", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("TotemSwap", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("InventoryTotem", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoOffhand", "COMBAT") { { this.mode = "TOTEM"; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoArmor", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ArmorSwap", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoGapple", "COMBAT") { { this.value1 = 15.0; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoHeal", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoPotion", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoSoup", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoPearl", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoLeave", "COMBAT") { { this.value1 = 4.0; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AutoDisconnect", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AntiVoid", "COMBAT") { @Override public void onTick() {} });

        // ==========================================
        // 3. CRYSTAL PvP CORE & ANCHOR PvP
        // ==========================================
        ModuleManager.addModule(new Module("AutoCrystal", "COMBAT") { { this.value1 = 4.0; this.mode = "BREAK"; } @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalAura", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalPlace", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalBreak", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalPredict", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorAura", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorPlace", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorBreak", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("HoleFill", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Surround", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("SelfTrap", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Burrow", "COMBAT") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ObsidianExtender", "COMBAT") { @Override public void onTick() {} });
    }
}
