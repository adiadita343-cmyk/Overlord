package com.adiadita343;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class OverlordMod implements ModInitializer {
    private static KeyBinding guiKey;

    @Override
    public void onInitialize() {
        // Inițializăm modulele
        ModuleManager.init();
        
        // Înregistrăm tasta RIGHT SHIFT pentru meniu
        guiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Open Menu", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_RIGHT_SHIFT, 
                "Overlord"
        ));

        // Conectăm logica la joc (Tick Event)
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            
            // Deschide ClickGUI
            while (guiKey.wasPressed()) {
                client.setScreen(new ClickGuiScreen());
            }
            
            // Rulează hack-urile
            ModuleManager.onTick();
        });
    }
}
