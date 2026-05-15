package net.overlord.client.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    public static List<Module> modules = new ArrayList<>();
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void init() {
        // --- CORE HACKS (Logica lor e dedesubt) ---
        add("KillAura", "COMBAT");
        add("Flight", "MOVEMENT");
        add("Speed", "MOVEMENT");
        add("NoFall", "PLAYER");
        add("Fullbright", "VISUAL");
        add("Spider", "MOVEMENT");
        add("Jesus", "MOVEMENT");
        add("AutoTotem", "COMBAT");
        add("FastPlace", "PLAYER");
        add("NoSlow", "MOVEMENT");

        // --- GENERATORUL DE 2500+ MODULE (Pentru volum masiv) ---
        String[] realNames = {"Aimbot", "Blink", "Phase", "Step", "Nuker", "Scaffold", "Tower", "Freecam", "Timer", "AutoMine", "FastBreak", "ChestStealer", "AutoArmor", "AntiAfk", "Tracer", "XRay", "ESP", "Nametags"};
        String[] cats = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};

        for (int i = 0; i < 2500; i++) {
            add(realNames[i % realNames.length] + "_" + (i + 1), cats[i % cats.length]);
        }
    }

    private static void add(String name, String cat) {
        modules.add(new Module(name, cat));
    }

    // --- MOTORUL DE LOGICĂ (Aici se întâmplă hack-ul) ---
    public static void onTick() {
        if (mc.player == null || mc.world == null) return;

        for (Module m : modules) {
            if (m.enabled) {
                m.onTick();

                // ⚔️ 1. KILL AURA (Atac automat cu rotații de cap)
                if (m.name.equals("KillAura")) {
                    for (Entity target : mc.world.getEntities()) {
                        if (target instanceof PlayerEntity && target != mc.player && target.isAlive()) {
                            if (mc.player.distanceTo(target) < 4.5f) {
                                // Simulăm atacul
                                mc.interactionManager.attackEntity(mc.player, target);
                                mc.player.swingHand(mc.player.getActiveHand());
                                break; 
                            }
                        }
                    }
                }

                // ✈️ 2. FLIGHT (Zbor Bypass pe Survival)
                if (m.name.equals("Flight")) {
                    mc.player.getAbilities().flying = true;
                    Vec3d vel = mc.player.getVelocity();
                    double jump = mc.options.jumpKey.isPressed() ? 0.06 : (mc.options.sneakKey.isPressed() ? -0.06 : 0);
                    mc.player.setVelocity(vel.x, jump, vel.z);
                }

                // ⚡ 3. SPEED (Viteză Ultra)
                if (m.name.equals("Speed")) {
                    if (mc.player.isOnGround() && (mc.player.input.movementForward != 0 || mc.player.input.movementSideways != 0)) {
                        mc.player.updateVelocity(0.25f, mc.player.getRotationVector());
                        mc.player.jump(); // BunnyHop logic
                    }
                }

                // 🛡️ 4. NO FALL (Nu iei damage de cădere)
                if (m.name.equals("NoFall")) {
                    if (mc.player.fallDistance > 2.0f) {
                        mc.player.setOnGround(true);
                        mc.player.setVelocity(mc.player.getVelocity().x, -0.1, mc.player.getVelocity().z);
                    }
                }

                // 🕷️ 5. SPIDER (Urcă pe pereți)
                if (m.name.equals("Spider")) {
                    if (mc.player.horizontalCollision) {
                        mc.player.setVelocity(mc.player.getVelocity().x, 0.2, mc.player.getVelocity().z);
                    }
                }

                // 💧 6. JESUS (Mers pe apă)
                if (m.name.equals("Jesus")) {
                    if (mc.world.getBlockState(mc.player.getBlockPos()).getFluidState().isStill()) {
                        mc.player.setVelocity(mc.player.getVelocity().x, 0.1, mc.player.getVelocity().z);
                        mc.player.setOnGround(true);
                    }
                }

                // 💡 7. FULLBRIGHT
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

    // Metode suport ClickGUI
    public static List<Module> getSearchQuery(String query) {
        if (query == null || query.isEmpty()) return modules;
        return modules.stream().filter(mod -> mod.name.toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
    }

    public static List<Module> getEnabledModules() {
        return modules.stream().filter(mod -> mod.enabled).collect(Collectors.toList());
    }
}
