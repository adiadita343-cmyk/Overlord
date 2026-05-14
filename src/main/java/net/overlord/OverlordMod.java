package com.adiadita343;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class OverlordMod implements ModInitializer {
    private boolean shiftPressed = false;

    @Override
    public void onInitialize() {
        ModuleManager.init();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.getWindow() == null) return;

            // Verificăm tasta Right Shift
            boolean isDown = InputUtil.isKeyPressed(client.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT);
            
            if (isDown && !shiftPressed) {
                if (client.currentScreen instanceof ClickGUI) {
                    client.setScreen(null);
                } else {
                    client.setScreen(new ClickGUI());
                }
                shiftPressed = true;
            } else if (!isDown) {
                shiftPressed = false;
            }

            if (client.player != null) {
                for (Module m : ModuleManager.modules) {
                    m.onTick();
                }
            }
        });
    }
}
