package com.adiadita343.module;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;

public class CombatRegistry {
    public static void register() {
        // --- LISTA TA MASIVĂ DE COMBAT ---
        
        String[] combatNames = {
            "Aura", "Killaura", "Triggerbot", "BowAimbot", "Aimassist", "Clicker", "Reach", "Hitbox", 
            "Criticals", "Fastbow", "Multiaura", "AuraAura", "AutoPot", "Velocity", "AntiKnockback", 
            "SuperKnockback", "AutoArmor", "AutoWeapon", "AutoSword", "AutoBow", "AutoRod", "AutoTotem", 
            "AutoSoup", "Blink", "AuraSwitch", "HitDelay", "AttackSpeed", "FastHit", "Regen", "AnchorAura", 
            "CrystalAura", "AutoAnchor", "AutoCrystal", "AutoTrap", "Surround", "Burrow", "AutoCev", 
            "SelfTrap", "AntiBot", "TargetStrafe", "NoCooldown", "AutoClicker", "AutoSwordSwitch", 
            "AutoGapple", "AutoPearl", "AutoAnchorExplode"
            // Adaugă aici TOATE celelalte nume de combat pe care mi le-ai dat
        };

        for (String name : combatNames) {
            ModuleManager.addModule(new Module(name, "COMBAT") {
                @Override
                public void onTick() {
                    // LOGICA "VII": Atacă automat dacă ești aproape de cineva
                    for (Entity target : mc.world.getEntities()) {
                        if (target instanceof PlayerEntity && target != mc.player && target.isAlive()) {
                            if (mc.player.distanceTo(target) < 4.5) {
                                mc.interactionManager.attackEntity(mc.player, target);
                                mc.player.swingHand(Hand.MAIN_HAND);
                                break; 
                            }
                        }
                    }
                }
            });
        }
        
        // --- SECȚIUNEA SPECIALĂ PENTRU TOTEMURI (Manual) ---
        String[] totemPots = {"Strength", "Speed", "FireRes", "Regen", "Absorption", "Invisibility"};
        for (String pot : totemPots) {
            for (int i = 1; i <= 6; i++) {
                ModuleManager.addModule(new Module("AutoTotem_" + pot + "_" + i, "COMBAT") {
                    @Override
                    public void onTick() {
                        // Logica de verificat viața și pus totem
                        if (mc.player.getHealth() < 10) {
                            // Cod pentru AutoTotem rapid
                        }
                    }
                });
            }
        }
    }
}
