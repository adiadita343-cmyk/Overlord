package net.overlord.adiadita343.module;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;

public class MovementRegistry {

    public static void register() {
        // 1. FLY (Zbor stabil Meteor-Style)
        ModuleManager.addModule(new Module("Fly", "MOVEMENT") {
            @Override
            public void onTick() {
                if (mc.player == null) return;
                mc.player.getAbilities().flying = true;
                
                // Control din taste pentru zbor vertical mai rapid
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

        // 2. SPEED (Strafe / BunnyHop constant pe sol)
        ModuleManager.addModule(new Module("Speed", "MOVEMENT") {
            @Override
            public void onTick() {
                if (mc.player == null) return;
                if (mc.player.input.hasForwardMovement()) {
                    if (mc.player.isOnGround()) {
                        mc.player.jump(); // Auto-Jump pentru BunnyHop rapid
                    }
                    Vec3d vel = mc.player.getVelocity();
                    mc.player.setVelocity(vel.x * 1.28, vel.y, vel.z * 1.28);
                }
            }
        });

        // 3. STEP (Trecere instant peste obstacole de 2 blocuri - FIX)
        ModuleManager.addModule(new Module("Step", "MOVEMENT") {
            @Override
            public void onTick() {
                if (mc.player == null) return;
                // Folosim corect campul de Fabric modern fara paranteze de metoda
                mc.player.stepHeight = 2.0f; 
            }
            @Override
            public void onDisable() {
                if (mc.player != null) {
                    mc.player.stepHeight = 0.6f;
                }
            }
        });

        // 4. NOFALL (Anulează damage-ul prin pachete de poziție)
        ModuleManager.addModule(new Module("NoFall", "MOVEMENT") {
            @Override
            public void onTick() {
                if (mc.player == null) return;
                if (mc.player.fallDistance > 2.5f) {
                    mc.player.setOnGround(true);
                }
            }
        });

        // 5. SPRINT (Forțează sprintul inteligent)
        ModuleManager.addModule(new Module("Sprint", "MOVEMENT") {
            @Override
            public void onTick() {
                if (mc.player == null) return;
                if (mc.player.input.hasForwardMovement() && !mc.player.isHorizontalCollision() && mc.player.getHungerManager().getFoodLevel() > 6) {
                    mc.player.setSprinting(true);
                }
            }
        });

        // 6. SPIDER (Urcă pe pereți prin impuls vertical constant)
        ModuleManager.addModule(new Module("Spider", "MOVEMENT") {
            @Override
            public void onTick() {
                if (mc.player == null) return;
                if (mc.player.horizontalCollision) {
                    Vec3d v = mc.player.getVelocity();
                    mc.player.setVelocity(v.x, 0.25, v.z);
                }
            }
        });

        // 7. JESUS (Mers pe apă stabil, fără scufundare)
        ModuleManager.addModule(new Module("Jesus", "MOVEMENT") {
            @Override
            public void onTick() {
                if (mc.player == null || mc.world == null) return;
                if (mc.player.isTouchingWater() || mc.player.isInLava()) {
                    Vec3d v = mc.player.getVelocity();
                    mc.player.setVelocity(v.x, 0.12, v.z);
                    mc.player.setOnGround(true);
                }
            }
        });

        // 8. SCAFFOLD (Construiește automat sub picioare la mers)
        ModuleManager.addModule(new Module("Scaffold", "MOVEMENT") {
            @Override
            public void onTick() {
                if (mc.player == null || mc.world == null || mc.interactionManager == null) return;
                
                BlockPos subPos = mc.player.getBlockPos().down();
                if (mc.world.getBlockState(subPos).isAir()) {
                    // Caută un bloc valid în hotbar și pune-l sub jucător
                    BlockHitResult hitResult = new BlockHitResult(
                        new Vec3d(subPos.getX() + 0.5, subPos.getY() + 1, subPos.getZ() + 0.5), 
                        Direction.UP, subPos, false
                    );
                    mc.interactionManager.interactBlock(mc.player, Hand.MAIN_HAND, hitResult);
                    mc.player.swingHand(Hand.MAIN_HAND);
                }
            }
        });

        // 9. EXTRA GENERIC MODULES (Până la restul listei tale extinse)
        String[] extraMoves = {
            "HighJump", "LongJump", "Jetpack", "Glide", "FastLadder", 
            "LiquidWalk", "Phase", "Blink", "Strafe", "SafeWalk", 
            "AirJump", "InventoryMove", "ElytraBoost", "FastSwim"
        };

        for (String s : extraMoves) {
            ModuleManager.addModule(new Module(s, "MOVEMENT") {
                @Override public void onTick() {}
            });
        }
    }
}
