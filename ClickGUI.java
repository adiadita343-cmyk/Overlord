package com.adiadita343;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import java.util.List;

public class ClickGUI extends Screen {
    public ClickGUI() {
        super(Text.literal("Overlord ClickGUI"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        int xOffset = 10;
        String[] cats = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};

        for (String cat : cats) {
            context.fill(xOffset, 10, xOffset + 80, 25, 0xFF444444); // Header categorie
            context.drawTextWithShadow(textRenderer, cat, xOffset + 5, 15, 0xFFFFFF);
            
            List<Module> catMods = ModuleManager.getModulesByCategory(cat);
            int yOffset = 30;
            
            // Afișăm primele 20 de module din fiecare categorie (să nu iasă din ecran)
            for (int i = 0; i < 20; i++) {
                Module m = catMods.get(i);
                int color = m.enabled ? 0xFF00FF00 : 0xFFFF0000;
                context.drawTextWithShadow(textRenderer, m.name, xOffset + 5, yOffset, color);
                yOffset += 12;
            }
            xOffset += 90;
        }
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Aici adăugăm logica de click pentru toggle la module
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() { return false; }
}
