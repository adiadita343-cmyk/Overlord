package com.adiadita343.overlord.module;

public class VisualRegistry {
    public static void register() {
        // === 🧭 Base & Storage Finders ===
        ModuleManager.addModule(new Module("Basefinder", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AdvancedBasefinder", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("StorageESP", "VISUAL") { @Override public void onTick() {} });

        // === 👁️ Entity Visuals (ESP Family) ===
        ModuleManager.addModule(new Module("ESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("PlayerESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("MobESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnimalESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ItemESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ChestESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("EnderChestESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ShulkerESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CrystalESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AnchorESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("VehicleESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ProjectileESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("XPOrbESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("DroppedItemESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("BaseESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("HoleESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("BurrowESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("FriendESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("EnemyESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("TeamESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("TargetESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("DeathSpotESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AntiBotESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("InvisibleEntityESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ArmorStandESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("VillagerESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("AllEntityESP", "VISUAL") { @Override public void onTick() {} });

        // === 🧭 Render Overlays & Tags ===
        ModuleManager.addModule(new Module("Tracers", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Nametags", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("HealthTags", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ArmorTags", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("PingTags", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("PopCounter", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("TotemPopESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("DamageIndicator", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("HitMarker", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("TargetHUD", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Radar", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Chams", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("SkeletonESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("TrailRender", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Breadcrumbs", "VISUAL") { @Override public void onTick() {} });

        // === 🌈 World Render Visuals ===
        ModuleManager.addModule(new Module("Fullbright", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("BrightnessOverride", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("GammaOverride", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("NoFog", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("NoWeather", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("WeatherChanger", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("TimeChanger", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CaveBrightness", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("XRay", "VISUAL") { @Override public void onTick() {} });

        // === 🎥 Camera & Lighting ===
        ModuleManager.addModule(new Module("Freecam", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("CameraClip", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Zoom", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("FreeLook", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("NoRender", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("NoBlindness", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("NoNausea", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("NoFireOverlay", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("NoPortalOverlay", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("ViewModelChanger", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("FOVChanger", "VISUAL") { @Override public void onTick() {} });
        
        // === 🧱 Block / World Visual Helpers ===
        ModuleManager.addModule(new Module("BlockOutline", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("BlockHighlight", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("MiningESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("SpawnerESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("OreESP", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("Search", "VISUAL") { @Override public void onTick() {} });
        ModuleManager.addModule(new Module("HUDEditor", "VISUAL") { @Override public void onTick() {} });
    }
}
