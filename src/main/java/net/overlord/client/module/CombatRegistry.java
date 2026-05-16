package net.overlord.com.adiadita343.module;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class CombatRegistry {
    public static void register() {
        // --- AURAS & PVP CORE ---
        String[] pvpMods = {
            "KillAura", "TriggerBot", "AimAssist", "AutoClicker", "MultiAura", 
            "TPAura", "InfiniteAura", "LegitAura", "SwitchAura", "AuraAura", 
            "Reach", "Criticals", "Velocity", "AntiKnockback", "SuperKnockback"
        };

        for (String s : pvpMods) {
            ModuleManager.addModule(new Module(s, "COMBAT") {
                @Override
                public void onTick() {
                    for (Entity e : mc.world.getEntities()) {
                        if (e instanceof PlayerEntity && e != mc.player && e.isAlive() && mc.player.distanceTo(e) < 4.5) {
                            mc.interactionManager.attackEntity(mc.player, e);
                            mc.player.swingHand(Hand.MAIN_HAND);
                        }
                    }
                }
            });
        }

        // --- AUTO TOTEM UNDETECTABLE (Scanare Inventar) ---
        ModuleManager.addModule(new Module("AutoTotem", "COMBAT") {
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

        // --- CRYSTAL & ANCHOR (Explozii Elite) ---
        String[] bang = {"CrystalAura", "AutoCrystal", "AnchorAura", "AutoAnchor", "AutoCev", "BedAura"};
        for (String b : bang) ModuleManager.addModule(new Module(b, "COMBAT") { @Override public void onTick() {} });
    }
}
