package com.adiadita343;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import java.util.List;

public class ClickGuiScreen extends Screen {

    public ClickGuiScreen() {
        super(Text.literal("Overlord ClickGUI"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Desenăm fundalul standard (întunecat)
        this.renderBackground(context, mouseX, mouseY, delta);

        int xOffset = 20;
        String[] categorii = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};

        for (String cat : categorii) {
            // Desenăm chenarul pentru capul categoriei
            context.fill(xOffset, 20, xOffset + 90, 35, 0xFF111111);
            context.drawTextWithShadow(this.textRenderer, cat, xOffset + 5, 24, 0xFF55FF55); // Text verde

            // Luăm modulele din această categorie
            List<Module> moduleDinCategorie = ModuleManager.getModulesByCategory(cat);
            
            int yOffset = 38;
            for (Module m : moduleDinCategorie) {
                // Dacă modulul e activ, îl facem albastru/verde, altfel gri
                int culoareFundal = m.enabled ? 0xFF2244AA : 0xFF333333;
                
                context.fill(xOffset, yOffset, xOffset + 90, yOffset + 12, culoareFundal);
                context.drawTextWithShadow(this.textRenderer, m.name, xOffset + 4, yOffset + 2, 0xFFFFFFFF);
                
                yOffset += 14; // Spațiu între module
            }
            
            xOffset += 100; // Mutăm următoarea coloană la dreapta
        }

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false; // Jocul nu se oprește când deschizi meniul (important pe servere)
    }
}
