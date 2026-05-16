package com.adiadita343.module;

public class VisualRegistry {
    public static void register() {
        String v = "VISUAL";

        // --- MODULE DE BAZĂ ---
        String[] baseVisuals = {
            "Fullbright", "Xray", "ESP", "Wallhack", "NoRender", "NoHurtCam", "NoWeather", 
            "Tracers", "Nametags", "Freecam", "Nightvision", "Brightness", "Breadcrumbs", 
            "CameraClip", "Radar", "Direction", "ChunkBorder", "Highlight"
        };

        for (String name : baseVisuals) {
            ModuleManager.addModule(new Module(name, v) {
                @Override
                public void onTick() {
                    if (name.contains("Fullbright") || name.contains("Xray") || name.contains("Nightvision")) {
                        mc.options.getGamma().setValue(100.0);
                    }
                }
                @Override
                public void onDisable() {
                    mc.options.getGamma().setValue(1.0);
                }
            });
        }

        // --- ESP PENTRU TOATE ENTITĂȚILE ȘI BLOCURILE (Sute de module) ---
        String[] espTypes = {
            "Player", "Mob", "Item", "Chest", "EnderChest", "ShulkerBox", "Furnace", "Spawner", 
            "Beacon", "Bed", "Portal", "TNT", "EnderCrystal", "ExperienceOrb", "Arrow", 
            "Trident", "FallingBlock", "Villager", "ArmorStand", "Boat", "Minecart"
        };

        for (String type : espTypes) {
            ModuleManager.addModule(new Module("ESP_" + type, v) {
                @Override public void onTick() {
                    // Aici randezi box-urile în jurul entității 'type'
                }
            });
            // Adăugăm și variantele de stil pentru fiecare
            String[] styles = {"Box", "Outline", "Glow", "2D", "Shader"};
            for (String style : styles) {
                ModuleManager.addModule(new Module("ESP_" + type + "_" + style, v) {
                    @Override public void onTick() {}
                });
            }
        }
    }
}
