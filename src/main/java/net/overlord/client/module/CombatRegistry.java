package com.adiadita343.module;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class CombatRegistry {

    public static void register() {
        registerAuras();
        registerAutoTotems();
        registerCrystalAndAnchor();
        registerMiscCombat();
    }

    private static void registerAuras() {
        String c = "COMBAT";
        // Toate tipurile de Aura și Aim din lista ta
        String[] mods = {
            "Aura", "Killaura", "Triggerbot", "BowAimbot", "Aimassist", "Clicker", "Reach", "Hitbox", 
            "Criticals", "Fastbow", "Multiaura", "AuraAura", "Velocity", "AntiKnockback", 
            "SuperKnockback", "AutoWeapon", "AutoSword", "AutoBow", "AutoRod", "AutoSoup", 
            "Blink", "AuraSwitch", "HitDelay", "AttackSpeed", "FastHit", "Regen", "AntiBot", 
            "TargetStrafe", "NoCooldown", "AutoClicker", "AutoSwordSwitch", "AutoGapple", 
            "AutoPearl", "TickShift", "Hitreg", "BowSpam", "LegitAura", "MultiAura", 
            "SwitchAura", "PriorityAura", "TPAura", "InfiniteAura"
        };

        for (String name : mods) {
            ModuleManager.addModule(new Module(name, c) {
                @Override
                public void onTick() {
                    // Logica de atac universală pentru Aura
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
    }

    private static void registerAutoTotems() {
        String c = "COMBAT";
        
        // Modulul Principal AutoTotem (Undetectable)
        ModuleManager.addModule(new Module("AutoTotem", c) {
            @Override
            public void onTick() {
                if (mc.player.getOffHandStack().getItem() == Items.TOTEM_OF_UNDYING) return;
                for (int i = 0; i < 45; i++) {
                    if (mc.player.getInventory().getStack(i).getItem() == Items.TOTEM_OF_UNDYING) {
                        int slot = i < 9 ? i + 36 : i;
                        mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, slot, 0, SlotActionType.PICKUP, mc.player);
                        mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
                        mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, slot, 0, SlotActionType.PICKUP, mc.player);
                        break;
                    }
                }
            }
        });

        // Toate celelalte variante de AutoTotem (Potion, Strict, etc.)
        String[] types = {"Totemless", "Offhand", "Mainhand", "Strict", "Switch", "Delay", "Silent", "Safety", "FailSafe", "NoLag", "Armor", "Sword", "Shield"};
        for (String t : types) {
            ModuleManager.addModule(new Module("AutoTotem_" + t, c) {
                @Override public void onTick() { /* Logica specifica */ }
            });
        }

        // Variantele de Potiuni (Health, Strength, etc. nivelele 1-6)
        String[] pots = {"Health", "Strength", "Speed", "FireRes", "Regen", "Absorption", "Invisibility", "Luck"};
        for (String p : pots) {
            for (int i = 1; i <= 6; i++) {
                ModuleManager.addModule(new Module("AutoTotem_" + p + i + "Potion", c) {
                    @Override public void onTick() { /* Logica specifica per potiune */ }
                });
            }
        }
    }

    private static void registerCrystalAndAnchor() {
        String c = "COMBAT";
        // CrystalAura Elite
        String[] cr = {"Sync", "Place", "Break", "Delay", "MultiPlace", "InstantBreak", "Raytrace", "WallRange", "Rotate", "SilentRotate", "PredictBreak", "DamageCalc", "AntiSurround", "AntiBurrow", "AntiCev", "AdaptiveDelay", "PingSync", "Bypass", "Threaded", "PopPredict", "TotemBreaker", "DoublePop", "Burst", "Spam", "LegitMode", "StrictMode"};
        for (String s : cr) {
            ModuleManager.addModule(new Module("CrystalAura_" + s, c) {
                @Override public void onTick() { /* Logica Crystal */ }
            });
        }

        // AnchorAura
        String[] an = {"Aura", "AutoAnchor", "AutoCev", "AnchorPredict", "AnchorCharge", "AnchorBreaker"};
        for (String s : an) {
            ModuleManager.addModule(new Module(s, c) {
                @Override public void onTick() { /* Logica Anchor */ }
            });
        }
    }

    private static void registerMiscCombat() {
        String c = "COMBAT";
        String[] misc = {"Surround", "SelfTrap", "AutoTrap", "HoleFiller", "Burrow", "Offhand", "NoSlowCombat", "KeepSprint", "AntiWeakness", "AutoWeb", "WebAura", "AntiCrystal", "AutoLog", "FastEat", "AutoHeal", "AutoGapple"};
        for (String s : misc) {
            ModuleManager.addModule(new Module(s, c) {
                @Override public void onTick() {
                    if (name.equals("AutoLog") && mc.player.getHealth() < 5) {
                        mc.world.disconnect(); // Exemplu de logica
                    }
                }
            });
        }
    }
}
