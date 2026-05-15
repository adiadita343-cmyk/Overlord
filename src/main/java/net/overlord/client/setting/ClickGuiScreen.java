package com.adiadita343;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class ClickGuiScreen extends Screen {
    private TextFieldWidget searchBar;

    public ClickGuiScreen() {
        super(Text.literal("Overlord ClickGUI"));
    }

    @Override
    protected void init() {
        // 1. ADĂUGĂM SEARCH BAR-UL
        this.searchBar = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, 20, 200, 20, Text.literal("Search..."));
        this.searchBar.setPlaceholder(Text.literal("Search modules..."));
        this.addSelectableChild(this.searchBar);
        
        // Face cursorul să apară automat în search bar
        this.setInitialFocus(this.searchBar);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // 2. SCOATEM BLUR-UL (Nu mai apelăm renderBackground)
        // Desenăm doar un strat negru foarte transparent ca să vedem jocul clar în spate
        context.fill(0, 0, this.width, this.height, 0x44000000); 

        // Desenăm Search Bar-ul
        this.searchBar.render(context, mouseX, mouseY, delta);

        // 3. DESENĂM COLOANELE (Combat, Movement, etc.)
        int xOffset = 50;
        String[] categories = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        
        for (String cat : categories) {
            context.drawTextWithShadow(this.textRenderer, cat, xOffset, 60, 0xFFFFFF);
            
            int yOffset = 80;
            for (Module m : ModuleManager.modules) {
                if (m.category.equals(cat)) {
                    // Dacă scriem ceva în search, filtrăm modulele
                    if (!searchBar.getText().isEmpty() && !m.name.toLowerCase().contains(searchBar.getText().toLowerCase())) {
                        continue;
                    }

                    // Desenăm butonul (Verde dacă e pornit, Gri dacă e oprit)
                    int color = m.enabled ? 0xFF00FF00 : 0xFF888888;
                    context.fill(xOffset, yOffset, xOffset + 80, yOffset + 15, 0x88000000); // Fundal buton
                    context.drawTextWithShadow(this.textRenderer, m.name, xOffset + 5, yOffset + 3, color);
                    
                    yOffset += 20;
                }
            }
            xOffset += 100;
        }
        super.render(context, mouseX, mouseY, delta);
    }

    // 4. FACEM BUTOANELE SĂ POATĂ FI APĂSATE
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.searchBar.mouseClicked(mouseX, mouseY, button)) return true;

        int xOffset = 50;
        String[] categories = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};

        for (String cat : categories) {
            int yOffset = 80;
            for (Module m : ModuleManager.modules) {
                if (m.category.equals(cat)) {
                    // Verificăm dacă mouse-ul este peste buton când dăm click
                    if (mouseX >= xOffset && mouseX <= xOffset + 80 && mouseY >= yOffset && mouseY <= yOffset + 15) {
                        m.toggle(); // Pornește/Oprește modulul
                        return true;
                    }
                    yOffset += 20;
                }
            }
            xOffset += 100;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    // 5. PERMITEM JOCULUI SĂ NU SE OPREASCĂ (Dacă vrei să te miști cu meniul deschis)
    @Override
    public boolean shouldPause() {
        return false;
    }
}
