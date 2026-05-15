package com.adiadita343;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import java.awt.Color;

public class ClickGuiScreen extends Screen {
    private TextFieldWidget searchBar;
    private Module bindingModule = null;

    public ClickGuiScreen() {
        super(Text.literal("Overlord Apex"));
    }

    @Override
    protected void init() {
        // Search Bar - Stil Meteor/Future
        this.searchBar = new TextFieldWidget(this.textRenderer, this.width / 2 - 75, 12, 150, 14, Text.literal(""));
        this.searchBar.setPlaceholder(Text.literal("Search..."));
        this.searchBar.setDrawsBackground(false);
        this.addSelectableChild(this.searchBar);
        this.setInitialFocus(this.searchBar);
        this.searchBar.setFocused(true);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // ZERO BLUR: Nu apelăm super.render(context, mouseX, mouseY, delta) la început!
        // Desenăm un fundal negru extrem de fin (10% opacitate)
        context.fill(0, 0, this.width, this.height, 0x1A000000); 

        // Randare Design Search Bar
        int rainbow = Color.HSBtoRGB((System.currentTimeMillis() % 6000) / 6000f, 0.7f, 1f);
        context.fill(this.width / 2 - 80, 10, this.width / 2 + 80, 28, 0xCC111111);
        context.fill(this.width / 2 - 80, 27, this.width / 2 + 80, 28, rainbow);
        this.searchBar.render(context, mouseX, mouseY, delta);

        int x = 20;
        String[] categories = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        String filter = searchBar.getText().toLowerCase();

        for (String cat : categories) {
            // Panel Header
            context.fill(x - 2, 35, x + 92, 48, 0xFF151515);
            context.fill(x - 2, 47, x + 92, 48, rainbow);
            context.drawTextWithShadow(this.textRenderer, "§l" + cat, x + 4, 37, 0xFFFFFF);

            int y = 52;
            for (Module m : ModuleManager.modules) {
                if (m.category.equalsIgnoreCase(cat)) {
                    // Filtrare Search
                    if (!filter.isEmpty() && !m.name.toLowerCase().contains(filter)) continue;

                    boolean isHovered = mouseX >= x && mouseX <= x + 90 && mouseY >= y && mouseY <= y + 12;
                    
                    // Design Buton: Albastru dacă e ON, Gri dacă e OFF
                    int btnColor = m.enabled ? (rainbow & 0x44FFFFFF) | 0x66000000 : (isHovered ? 0xAA444444 : 0xAA222222);
                    context.fill(x, y, x + 90, y + 12, btnColor);

                    // Text Modul
                    String text = (bindingModule == m) ? "§bBIND..." : (m.enabled ? "§f" + m.name : "§7" + m.name);
                    context.drawTextWithShadow(this.textRenderer, text, x + 4, y + 2, 0xFFFFFF);

                    y += 14;
                }
            }
            x += 105;
        }

        // --- ACTIVE MODULES HUD (Arraylist) ---
        renderHUD(context);
    }

    private void renderHUD(DrawContext context) {
        int yHUD = 5;
        for (Module m : ModuleManager.modules) {
            if (m.enabled) {
                int hudColor = Color.HSBtoRGB((System.currentTimeMillis() + (yHUD * 100)) % 6000 / 6000f, 0.6f, 1f);
                int textWidth = this.textRenderer.getWidth(m.name);
                context.drawTextWithShadow(this.textRenderer, m.name, this.width - textWidth - 5, yHUD, hudColor);
                yHUD += 10;
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.searchBar.mouseClicked(mouseX, mouseY, button)) return true;

        int x = 20;
        String[] categories = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        String filter = searchBar.getText().toLowerCase();

        for (String cat : categories) {
            int y = 52;
            for (Module m : ModuleManager.modules) {
                if (m.category.equalsIgnoreCase(cat)) {
                    if (!filter.isEmpty() && !m.name.toLowerCase().contains(filter)) continue;

                    if (mouseX >= x && mouseX <= x + 90 && mouseY >= y && mouseY <= y + 12) {
                        if (button == 0) m.toggle(); // CLICK STÂNGA = ACTIVARE
                        else if (button == 1) bindingModule = m; // CLICK DREAPTA = SETARE TASTĂ
                        return true;
                    }
                    y += 14;
                }
            }
            x += 105;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (bindingModule != null) {
            if (keyCode == GLFW.GLFW_KEY_ESCAPE) bindingModule.keyCode = 0;
            else bindingModule.keyCode = keyCode;
            bindingModule = null;
            return true;
        }
        if (this.searchBar.keyPressed(keyCode, scanCode, modifiers)) return true;
        if (keyCode == GLFW.GLFW_KEY_ESCAPE || keyCode == GLFW.GLFW_KEY_RIGHT_SHIFT) {
            this.close();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        return this.searchBar.charTyped(chr, modifiers);
    }

    @Override
    public boolean shouldPause() { return false; }
}
