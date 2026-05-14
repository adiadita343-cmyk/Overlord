package com.adiadita343;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Overlord implements ModInitializer {
    @Override
    public void onInitialize() {
        ModuleManager.init();
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // GLFW_KEY_RIGHT_SHIFT este codul pentru tasta dorită
            while (InputUtil.isKeyPressed(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT)) {
                if (client.currentScreen == null) {
                    client.setScreen(new ClickGUI());
                }
            }
            
            // Rulează logica pentru toate modulele activate
            for (Module m : ModuleManager.modules) {
                m.onTick();
            }
        });
    }
}
