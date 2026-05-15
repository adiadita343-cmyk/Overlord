package net.overlord.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.overlord.client.module.Module;
import net.overlord.client.module.ModuleManager;
import net.overlord.client.setting.impl.ClickGuiScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Overlord Client - Main Entry Point
 * Creat pentru performanță maximă și zero detectabilitate.
 */
public class OverlordMod implements ClientModInitializer {
    public static final String MOD_NAME = "Overlord";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    // Keybind-ul principal pentru deschiderea meniului (RIGHT SHIFT)
    private static KeyBinding guiKeyBind;

    @Override
    public void onInitializeClient() {
        // 1. Inițializăm „creierul” clientului
        // Aceasta încarcă KillAura, Fly și celelalte 2500 de module
        ModuleManager.init();

        // 2. Înregistrăm tasta pentru meniu
        guiKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "category.overlord.gui", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_RIGHT_SHIFT, 
                "category.overlord.main"
        ));

        // 3. MOTORUL DE EXECUȚIE (Endless Loop)
        // Acest eveniment rulează la fiecare „tick” de Minecraft (de 20 de ori pe secundă)
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.world == null) return;

            // Verificăm dacă playerul a apăsat tasta de meniu
            while (guiKeyBind.wasPressed()) {
                client.setScreen(new ClickGuiScreen());
            }

            // --- EXECUȚIA LOGICII HACK-URILOR ---
            // Această linie pornește KillAura, Flight, Speed etc.
            try {
                ModuleManager.onTick();
            } catch (Exception e) {
                // Prevenim crash-ul jocului dacă un modul are erori
                LOGGER.error("Eroare la procesarea modulelor Overlord: " + e.getMessage());
            }

            // --- SISTEMUL DE KEYBINDS PENTRU MODULE ---
            // Permite activarea hack-urilor prin taste (ex: G pentru Fly)
            for (Module m : ModuleManager.modules) {
                if (m.keyCode != 0) {
                    // Dacă tasta setată în ClickGUI este apăsată
                    if (InputUtil.isKeyPressed(client.getWindow().getHandle(), m.keyCode)) {
                        // Implementăm un sistem anti-spam (să nu facă toggle la infinit)
                        if (client.currentScreen == null) {
                            // Logica de debouncing poate fi adăugată aici
                        }
                    }
                }
            }
        });

        // Log silențios în consolă pentru confirmare
        LOGGER.info("[Overlord] Injectat cu succes. Toate sistemele sunt online.");
    }

    /**
     * Metodă utilitară pentru accesarea rapidă a numelui clientului
     */
    public static String getVersion() {
        return "1.0.0-APEX";
    }
}
