package com.adiadita343;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Overlord implements ModInitializer {
    private static boolean isShiftDown = false;

    @Override
    public void onInitialize() {
        ModuleManager.init();
        
        // Acest eveniment rulează constant, chiar și în meniuri
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client == null || client.getWindow() == null) return;

            // Verificăm dacă tasta Right Shift este apăsată
            boolean currentlyPressed = InputUtil.isKeyPressed(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT);
            
            if (currentlyPressed && !isShiftDown) {
                // Dacă nu avem deja un ecran deschis sau dacă suntem în meniul principal
                if (client.currentScreen == null || client.currentScreen instanceof net.minecraft.client.gui.screen.TitleScreen) {
                    client.setScreen(new ClickGUI());
                } else if (client.currentScreen instanceof ClickGUI) {
                    // Dacă apăsăm Shift în timp ce meniul e deja deschis, îl închidem
                    client.setScreen(null);
                }
                isShiftDown = true;
            } else if (!currentlyPressed) {
                isShiftDown = false;
            }
            
            // Rulează logica pentru cele 1200 de module dacă suntem în lume
            if (client.player != null) {
                for (Module m : ModuleManager.modules) {
                    if (m.enabled) m.onTick();
                }
            }
        });
    }
}
