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
 * Overlord Client v1.0 - Proiectat pentru dominare totală.
 * Acest entry point pornește toate sistemele critice în mod silențios.
 */
public class OverlordMod implements ClientModInitializer {
    public static final String MOD_ID = "overlord";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Tasta pentru deschiderea interfeței (Implicit: RIGHT_SHIFT)
    private static KeyBinding guiKeyBind;

    @Override
    public void onInitializeClient() {
        // 1. Inițializăm lista masivă de module (cele 2500+)
        // Aceasta trebuie să ruleze prima dată pentru a popula memoria
        ModuleManager.init();

        // 2. Înregistrăm tasta oficială pentru meniul Overlord
        guiKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.overlord.gui", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_RIGHT_SHIFT, 
                "category.overlord.main"
        ));

        // 3. EXECUTORUL DE TICKS (Sistemul de operare al clientului)
        // Rulează de 20 de ori pe secundă sincronizat cu Minecraft
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Verificăm dacă suntem într-o lume, altfel dăm crash
            if (client.player == null || client.world == null) return;

            // Logica pentru deschiderea GUI-ului
            while (guiKeyBind.wasPressed()) {
                client.setScreen(new ClickGuiScreen());
            }

            // --- PROCESAREA LOGICII HACK-URILOR ---
            // Această linie apelează metodele onTick din ModuleManager (KillAura, Fly, Speed)
            try {
                ModuleManager.onTick();
            } catch (Exception e) {
                // Dacă un hack „o ia razna”, prindem eroarea aici ca să nu închidem jocul
                LOGGER.error("[Overlord Error] Eroare critică în execuția modulelor: " + e.getMessage());
            }

            // --- SISTEMUL DE KEYBINDS PENTRU MODULE ---
            // Verifică dacă ai setat taste specifice pentru hack-uri (ex: F pentru Flight)
            for (Module m : ModuleManager.modules) {
                if (m.keyCode != 0 && client.currentScreen == null) {
                    // Verificăm dacă tasta este apăsată în acest moment
                    if (InputUtil.isKeyPressed(client.getWindow().getHandle(), m.keyCode)) {
                        // Aici se poate adăuga un toggle delay dacă vrei
                    }
                }
            }
        });

        // Mesaj de confirmare doar în consolă (Silențios în joc)
        LOGGER.info("Overlord Client: Toate sistemele de bypass sunt online. Succes!");
    }

    public static String getClientName() {
        return "Overlord Apex";
    }
}
