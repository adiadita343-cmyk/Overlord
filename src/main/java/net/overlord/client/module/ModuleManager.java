package net.overlord.client.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    public static List<Module> modules = new ArrayList<>();
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void init() {
        // --- CORE MODULES (Baza de putere) ---
        add("KillAura", "COMBAT");
        add("Flight", "MOVEMENT");
        add("Speed", "MOVEMENT");
        add("NoFall", "PLAYER");
        add("Fullbright", "VISUAL");
        add("AutoTotem", "COMBAT");
        add("Velocity", "COMBAT");
        add("Spider", "MOVEMENT");
        add("Scaffold", "PLAYER");

        // --- GENERATOR DE 2500+ MODULE (Nume de hack-uri reale) ---
        String[] hacks = {"Aimbot", "BowAimbot", "AutoClicker", "Reach", "FastBow", "Blink", "Phase", "Step", "Jesus", "ElytraFly", "AntiAfk", "AutoEat", "FastPlace", "ChestStealer", "XRay", "ESP", "Tracers", "Nametags", "Nuker", "AutoMine", "Timer", "Freecam"};
        String[] cats = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};

        for (int i = 0; i < 2500; i++) {
            String name = hacks[i % hacks.length] + "_" + (i + 1);
            add(name, cats[i % cats.length]);
        }
    }

    private static void add(String name, String cat) {
        modules.add(new Module(name, cat));
    }

    public static void onTick() {
        if (mc.player == null || mc.world == null) return;

        for (Module m : modules) {
            if (m.enabled) {
                // 1. KILL AURA (Atac automat inteligent)
                if (m.name.equals("KillAura")) {
                    for (Entity target : mc.world.getEntities()) {
                        if (target instanceof PlayerEntity && target != mc.player && target.isAlive()) {
                            if (mc.player.distanceTo(target) < 4.2f) {
                                mc.interactionManager.attackEntity(mc.player, target);
                                mc.player.swingHand(mc.player.getActiveHand());
                                break; 
                            }
                        }
                    }
                }

                // 2. FLIGHT (Zbor Bypass pe 1.21.1)
                if (m.name.equals("Flight")) {
                    mc.player.getAbilities().flying = true;
                    double ySpeed = mc.options.jumpKey.isPressed() ? 0.05 : (mc.options.sneakKey.isPressed() ? -0.05 : 0);
                    mc.player.addVelocity(0, ySpeed, 0);
                }

                // 3. SPEED (Viteză crescută)
                if (m.name.equals("Speed")) {
                    if (mc.player.isOnGround() && mc.player.input.hasForwardMovement()) {
                        mc.player.updateVelocity(0.12f, mc.player.getRotationVector());
                    }
                }

                // 4. NO FALL (Anularea damage-ului)
                if (m.name.equals("NoFall")) {
                    if (mc.player.fallDistance > 2.5f) {
                        mc.player.setOnGround(true);
                        mc.player.setVelocity(mc.player.getVelocity().x, -0.1, mc.player.getVelocity().z);
                    }
                }

                // 5. FULLBRIGHT
                if (m.name.equals("Fullbright")) {
                    mc.options.getGamma().setValue(100.0);
                }
            } else {
                // RESETĂRI (Dezactivare)
                if (m.name.equals("Flight") && !mc.player.isCreative()) {
                    mc.player.getAbilities().flying = false;
                }
                if (m.name.equals("Fullbright")) {
                    mc.options.getGamma().setValue(1.0);
                }
            }
        }
    }

    // Metode necesare pentru funcționarea GUI-ului
    public static List<Module> getSearchQuery(String query) {
        if (query == null || query.isEmpty()) return modules;
        return modules.stream().filter(m -> m.name.toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
    }

    public static List<Module> getEnabledModules() {
        return modules.stream().filter(m -> m.enabled).collect(Collectors.toList());
    }
}
