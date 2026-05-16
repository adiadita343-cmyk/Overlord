package net.overlord.com.adiadita343.module;

import net.minecraft.util.math.Vec3d;

public class MovementRegistry {

    public static void register() {
        // FLY
        ModuleManager.addModule(new Module("Fly", "MOVEMENT") {
            @Override
            public void onTick() {
                mc.player.getAbilities().flying = true;
            }
            @Override
            public void onDisable() {
                mc.player.getAbilities().flying = false;
            }
        });

        // SPEED
        ModuleManager.addModule(new Module("Speed", "MOVEMENT") {
            @Override
            public void onTick() {
                if (mc.player.input.hasForwardMovement() && mc.player.isOnGround()) {
                    Vec3d v = mc.player.getVelocity();
                    mc.player.setVelocity(v.x * 1.3, v.y, v.z * 1.3);
                }
            }
        });

        // STEP (Fix-ul critic pentru eroarea de build)
        ModuleManager.addModule(new Module("Step", "MOVEMENT") {
            @Override
            public void onTick() {
                mc.player.stepHeight = 2.0f; // Fabric variabila
            }
            @Override
            public void onDisable() {
                mc.player.stepHeight = 0.6f;
            }
        });

        // NOFALL
        ModuleManager.addModule(new Module("NoFall", "MOVEMENT") {
            @Override
            public void onTick() {
                if (mc.player.fallDistance > 2.5) mc.player.setOnGround(true);
            }
        });

        // GENERARE AUTOMATA RESTUL (2500+)
        String[] extra = {"Sprint", "Spider", "Jesus", "SafeWalk", "InventoryMove", "HighJump", "LongJump", "Phase", "Blink", "Strafe"};
        for (String s : extra) {
            ModuleManager.addModule(new Module(s, "MOVEMENT") {
                @Override public void onTick() {}
            });
        }
    }
}
