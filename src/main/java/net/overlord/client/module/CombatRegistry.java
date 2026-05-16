package net.overlord.com.adiadita343.module;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class CombatRegistry {

    public static void register() {
        // --- GRUPUL 1: AURAS (Toate tipurile de bataie) ---
        String[] auras = {
            "KillAura", "TriggerBot", "AimAssist", "AutoClicker", "MultiAura", 
            "TPAura", "InfiniteAura", "LegitAura", "SwitchAura", "AuraAura", 
            "Reach", "Criticals", "Velocity", "AntiKnockback", "SuperKnockback"
        };

        for (String s : auras) {
            ModuleManager.addModule(new Module(s, "COMBAT") {
                @Override
                public void onTick() {
                    // Logica de atac universala
                    for (Entity e : mc.world.getEntities()) {
                        if (e instanceof PlayerEntity && e != mc.player && e.isAlive() && mc.player.distanceTo(e) < 4.5) {
                            mc.interactionManager.attackEntity(mc.player, e);
                            mc.player.swingHand(Hand.MAIN_HAND);
                        }
                    }
                }
            });
        }

        // --- GRUPUL 2: CRYSTAL & ANCHOR (Explozii) ---
        String[] explosives = {
            "CrystalAura", "AutoCrystal", "AnchorAura", "AutoAnchor", "AutoCev", 
            "CevBreaker", "BedAura", "PistonAura", "AutoTrap", "Surround"
        };

        for (String s : explosives) {
            ModuleManager.addModule(new Module(s, "COMBAT") {
                @Override
                public void onTick() {
                    // Aici vine logica grea de Crystal (va fi adaugata pe parcurs)
                }
            });
        }

        // --- GRUPUL 3: AUTO TOTEM (Sistemul tau Undetectable) ---
        ModuleManager.addModule(new Module("AutoTotem", "COMBAT") {
            @Override
            public void onTick() {
                // Daca nu ai totem in mana stanga
                if (mc.player.getOffHandStack().getItem() == Items.TOTEM_OF_UNDYING) return;

                // Cauta in tot inventarul
                for (int i = 0; i < 45; i++) {
                    if (mc.player.getInventory().getStack(i).getItem() == Items.TOTEM_OF_UNDYING) {
                        // Fix slot pentru Fabric
                        int slot = i < 9 ? i + 36 : i;
                        
                        // Simularea click-urilor "Undetectable"
                        mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, slot, 0, SlotActionType.PICKUP, mc.player);
                        mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
                        mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, slot, 0, SlotActionType.PICKUP, mc.player);
                        break;
                    }
                }
            }
        });

        // --- GRUPUL 4: MISC COMBAT (Utilitati) ---
        String[] miscCombat = {
            "AutoGapple", "AutoPot", "AutoSoup", "AutoWeapon", "FastBow", 
            "BowAimbot", "Burrow", "SelfTrap", "HoleFiller", "AntiBot"
        };

        for (String s : miscCombat) {
            ModuleManager.addModule(new Module(s, "COMBAT") {
                @Override
                public void onTick() {
                    if (name.equals("AutoGapple") && mc.player.getHealth() < 15) {
                        // Logica mancat mar
                    }
                }
            });
        }
        
        System.out.println("[Overlord] Combat Registry a injectat " + auras.length + explosives.length + miscCombat.length + " module!");
    }
}
