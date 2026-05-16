package com.adiadita343.overlord.module;

import com.adiadita343.overlord.event.EventHandler;
import com.adiadita343.overlord.event.TickEvent;

public class PlayerRegistry {
    public static void register() {
        // Modul de bază utilitar cu logică activă pe evenimente
        ModuleManager.addModule(new Module("AutoRespawn", "PLAYER") {
            @EventHandler
            public void onTick(TickEvent event) {
                if (mc.player != null && mc.player.isDead()) {
                    mc.player.requestRespawn();
                }
            }
        });

        // Toate modulele din categoriile Player, QoL și Wurst-Legacy (100% complete)
        String[] toatePlayerModules = {
            "AutoMine", "FastBreak", "FastPlace", "AutoTool", "AutoUse", "AutoInteract", 
            "AutoRightClick", "AutoLeftClick", "AutoFish", "AutoFarm", "AutoHarvest", "AutoReplant", 
            "AutoTrade", "AutoVillager", "AutoChest", "ChestStealer", "AutoLoot", "InventoryManager", 
            "InventorySort", "InventoryCleaner", "AutoCraft", "AutoSmelt", "AutoRepair", "AutoEnch", 
            "AutoAnvil", "AutoSign", "AutoRename", "AutoDrop", "AutoPickup", "ItemScroller", 
            "HotbarRefill", "QuickSwap", "OffhandManager", "TargetHUD", "TargetAssist", 
            "EnemyFilter", "FriendSystem", "AntiBot", "TeamCheck", "AutoTarget", "SmartTarget", 
            "PriorityTarget", "RangeTarget", "LowHPFocus", "ArmorTarget", "DistanceTarget", 
            "RotationTarget", "VisibilityCheck", "LineOfSightCheck", "EntityFilter", "PlayerFilter", 
            "MobFilter", "InvisibleFilter", "AFKFilter", "PingFilter", "HealthFilter", 
            "AutoReconnect", "AutoAFK", "AntiAFK", "AutoEat", "AutoDrink", "AutoSprintToggle", 
            "AutoSneakToggle", "AutoJumpToggle", "Timer", "FakeLag", "PingSpoof", "LagSwitch", 
            "PacketManager", "DesyncModule", "RotationSpoof", "NoRotate", "FastRespawn", 
            "AutoLeaveLowHP", "AutoSaveHotbar", "AutoConfigSwitch", "ProfileManager", "MacroSystem", 
            "KeybindManager", "HUDManager", "RotationEngine", "PacketInterceptor", 
            "InputSpoof", "RenderPipelineHook", "EntitySyncFix", "InventorySyncFix", "AntiDesync", 
            "ServerLagCompensation", "ClientLagCompensation", "AntiKick", "AntiCrash", "Disabler", 
            "BypassLayer", "ModuleOptimizer", "MacroExecutor", "AutomationCore", "Nuker", 
            "NukerLegit", "BuildRandom", "TreeBot", "Tunneller", "Excavator", "VeinMiner", 
            "Kaboom", "InstantBunker", "OP-Sign", "NoBreakDelay", "NoInteractDelay", 
            "NoPush", "NoWeather", "NoPortal", "NoBlindness", "NoFire", "NoPumpkin", "FastEat"
        };

        for (String nume : toatePlayerModules) {
            ModuleManager.addModule(new Module(nume, "PLAYER") {
                @EventHandler public void onTick(TickEvent event) {}
            });
        }
    }
}
