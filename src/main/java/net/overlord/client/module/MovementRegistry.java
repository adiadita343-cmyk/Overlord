package net.overlord.adiadita343.module;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;

public class MovementRegistry {

    public static void register() {
        // 1. FLY
        ModuleManager.addModule(new Module("Fly", "MOVEMENT") {
            @Override
            public void onTick() {
                if (mc.player == null) return;
                mc.player.getAbilities().flying = true;
                
                Vec3d velocity = mc.player.getVelocity();
                double flySpeed = 0.5;
                if (mc.options.jumpKey.isPressed()) {
                    mc.player.setVelocity(velocity.x, flySpeed, velocity.z);
                } else if (mc.options.sneakKey.isPressed()) {
                    mc.player.setVelocity(velocity.x, -flySpeed, velocity.z);
                }
            }
            @Override
            public void onDisable() {
                if (mc.player != null && !mc.player.isCreative()) {
                    mc.player.getAbilities().flying = false;
                }
            }
        });

        // 2. SPEED
        ModuleManager.addModule(new Module("Speed", "MOVEMENT") {
            @Override
            public void onTick() {
                if (mc.player == null) return;
                if (mc.player.input.hasForwardMovement()) {
                    if (mc.player.isOnGround()) {
                        mc.player.jump();
                    }
                    Vec3d vel = mc.player.getVelocity();
                    mc.player.setVelocity(vel.x * 1.28, vel.y, vel.z * 1.28);
                }
            }
        });

        // 3. STEP
        ModuleManager.addModule(new Module("Step", "MOVEMENT") {
            @Override
            public void onTick() {
                // Lăsăm gol sau logică alternativă pentru a nu bloca compilarea în caz de nepotrivire de Yarn/Mojang mappings
            }
            @Override
            public void onDisable() {}
        });

        // 4. NOFALL
        ModuleManager.addModule(new Module("NoFall", "MOVEMENT") {
            @Override
            public void onTick() {
                if (mc.player == null) return;
                if (mc.player.fallDistance > 2.5f) {
                    mc.player.setOnGround(true);
                }
            }
        });

        // 5. SPRINT
        ModuleManager.addModule(new Module("Sprint", "MOVEMENT") {
            @Override
            public void onTick() {
                if (mc.player == null) return;
                if (mc.player.input.hasForwardMovement()) {
                    mc.player.setSprinting(true);
                }
            }
        });

        // 6. SCAFFOLD
        ModuleManager.addModule(new Module("Scaffold", "MOVEMENT") {
            @Override
            public void onTick() {
                if (mc.player == null || mc.world == null || mc.interactionManager == null) return;
                
                BlockPos subPos = mc.player.getBlockPos().down();
                if (mc.world.getBlockState(subPos).isAir()) {
                    BlockHitResult hitResult = new BlockHitResult(
                        new Vec3d(subPos.getX() + 0.5, subPos.getY() + 1, subPos.getZ() + 0.5), 
                        Direction.UP, subPos, false
                    );
                    mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hitResult);
                    mc.player.swingHand(Hand.MAIN_HAND);
                }
            }
        });

        // Generare module extra lungi
        String[] extraMoves = {"Spider", "Jesus", "HighJump", "LongJump", "Jetpack", "Glide", "Phase", "Blink", "Strafe", "SafeWalk"};
        for (String s : extraMoves) {
            ModuleManager.addModule(new Module(s, "MOVEMENT") {
                @Override public void onTick() {}
            });
        }
    }
}
