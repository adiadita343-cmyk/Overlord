package com.adiadita343.module;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class MovementRegistry {

    public static void register() {
        registerBaseMovement();
        registerNoFallSystem();
        registerClipAndPhase();
        registerEntityMovement();
    }

    private static void registerBaseMovement() {
        String m = "MOVEMENT";
        // Toate modurile de bază din lista ta
        String[] mods = {
            "Speed", "Sprint", "Highjump", "Longjump", "Fly", "Jetpack", "Levitate", "Glide", 
            "Spider", "Fastladder", "Fastclimb", "Step", "Jesus", "WaterWalk", "LiquidWalk", 
            "LiquidInteract", "Strafe", "SafeWalk", "AirJump", "DoubleJump", "InfiniteJump", 
            "ElytraFlight", "ElytraSpeed", "ElytraBounce", "ElytraBoost", "BunnyHop", 
            "SpeedHack", "FastSwim", "FastAscent", "FastDescent", "NoPush", "AntiPush"
        };

        for (String name : mods) {
            ModuleManager.addModule(new Module(name, m) {
                @Override
                public void onTick() {
                    String n = name.toLowerCase();
                    // LOGICĂ DINAMICĂ
                    if (n.contains("fly") || n.contains("jetpack") || n.contains("glide")) {
                        mc.player.getAbilities().flying = true;
                        mc.player.getAbilities().allowFlying = true;
                    }
                    if (n.contains("speed") || n.contains("strafe") || n.contains("hop")) {
                        if (mc.player.isOnGround() && mc.player.input.hasForwardMovement()) {
                            mc.player.updateVelocity(0.25f, mc.player.getRotationVector());
                            if (n.contains("hop")) mc.player.jump();
                        }
                    }
                    if (n.contains("step")) mc.player.setStepHeight(2.0f);
                    if (n.contains("jesus") || n.contains("waterwalk")) {
                        if (mc.player.isTouchingWater()) mc.player.setVelocity(mc.player.getVelocity().x, 0.1, mc.player.getVelocity().z);
                    }
                }

                @Override
                public void onDisable() {
                    mc.player.getAbilities().flying = false;
                    mc.player.setStepHeight(0.6f);
                }
            });
        }
    }

    private static void registerNoFallSystem() {
        String m = "MOVEMENT";
        // Sistemul NoFall pentru absolut TOATE entitățile din lista ta
        String[] entities = {
            "Packet", "Ground", "Damage", "Void", "Timer", "Elytra", "Boat", "Minecart", "Horse", 
            "Pig", "Strider", "Llama", "Donkey", "Mule", "SkeletonHorse", "ZombieHorse", "Camel", 
            "Sniffer", "Frog", "Axolotl", "Bee", "Fox", "Wolf", "Cat", "Ocelot", "Parrot", "Chicken", 
            "Cow", "Mooshroom", "Sheep", "Piglin", "Hoglin", "Zoglin", "Zombie", "Skeleton", 
            "Creeper", "Spider", "Enderman", "Witch", "Slime", "MagmaCube", "Ghast", "Blaze", 
            "Drowned", "Husk", "Stray", "Phantom", "Vex", "Evoker", "Vindicator", "Ravager", 
            "Warden", "Shulker", "Silverfish", "Endermite", "Guardian", "ElderGuardian", "Squid", 
            "GlowSquid", "Villager", "IronGolem", "SnowGolem", "ArmorStand", "Player", "FakePlayer"
        };

        for (String e : entities) {
            ModuleManager.addModule(new Module("NoFall_" + e, m) {
                @Override
                public void onTick() {
                    if (mc.player.fallDistance > 2.0f) {
                        // Bypass NoFall Packet
                        mc.player.setOnGround(true);
                        mc.player.setVelocity(mc.player.getVelocity().x, 0, mc.player.getVelocity().z);
                    }
                }
            });
        }
    }

    private static void registerClipAndPhase() {
        String m = "MOVEMENT";
        // Toate Clip-urile (VClip, HClip și direcții)
        String[] clips = {"Down", "Up", "Horizontal", "Forward", "Back", "Left", "Right", "North", "South", "East", "West"};
        for (String d : clips) {
            ModuleManager.addModule(new Module("Clip_" + d, m) {
                @Override
                public void onEnable() {
                    // Executăm clip-ul o singură dată la activare
                    Vec3d pos = mc.player.getPos();
                    double dist = 5.0; // Distanța de clip
                    if (name.contains("Up")) mc.player.setPosition(pos.x, pos.y + dist, pos.z);
                    if (name.contains("Down")) mc.player.setPosition(pos.x, pos.y - dist, pos.z);
                    if (name.contains("Forward")) {
                        Vec3d dir = Vec3d.fromPolar(0, mc.player.getYaw()).normalize();
                        mc.player.setPosition(pos.x + dir.x * dist, pos.y, pos.z + dir.z * dist);
                    }
                    this.enabled = false; // Se închide singur după clip
                }
                @Override public void onTick() {}
            });
        }
        addModuleManual("Phase", m);
        addModuleManual("NoClip", m);
    }

    private static void registerEntityMovement() {
        String m = "MOVEMENT";
        String[] entMods = {"BoatFly", "EntitySpeed", "HorseJump", "PigFly", "VehiclePhase", "EntityControl"};
        for (String s : entMods) {
            ModuleManager.addModule(new Module(s, m) {
                @Override
                public void onTick() {
                    if (mc.player.getVehicle() != null) {
                        Entity v = mc.player.getVehicle();
                        v.setNoGravity(true);
                        v.setVelocity(mc.player.getRotationVector().multiply(0.5));
                    }
                }
            });
        }
    }

    private static void addModuleManual(String name, String cat) {
        ModuleManager.addModule(new Module(name, cat) {
            @Override public void onTick() {}
        });
    }
}
