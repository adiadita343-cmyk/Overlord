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
    private String lastHoveredDesc = "Overlord Client | v1.0.0";

    public ClickGuiScreen() {
        super(Text.literal("Overlord Omega"));
    }

    @Override
    protected void init() {
        // Search Bar Futuristic (Centrat Sus)
        this.searchBar = new TextFieldWidget(this.textRenderer, this.width / 2 - 80, 10, 160, 18, Text.literal(""));
        this.searchBar.setPlaceholder(Text.literal("🔍 Search Modules..."));
        this.searchBar.setDrawsBackground(false);
        this.addSelectableChild(this.searchBar);
        this.setInitialFocus(this.searchBar);
        this.searchBar.setFocused(true);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // --- FUNDAL ZERO BLUR ---
        // Desenăm un strat de "vignette" (negru pe margini) pentru aspect profesional
        context.fill(0, 0, this.width, this.height, 0x44000000); 

        // Culoare Rainbow dinamică pentru tot meniul
        int rainbow = Color.HSBtoRGB((System.currentTimeMillis() % 5000) / 5000f, 0.7f, 1f);

        // --- SEARCH BAR DESIGN ---
        context.fill(this.width / 2 - 85, 8, this.width / 2 + 85, 30, 0xEE0A0A0A);
        context.fill(this.width / 2 - 85, 29, this.width / 2 + 85, 30, rainbow);
        this.searchBar.render(context, mouseX, mouseY, delta);

        int x = 15;
        String[] categories = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        String filter = searchBar.getText().toLowerCase();

        for (String cat : categories) {
            // --- HEADER CATEGORIE ---
            context.fill(x - 3, 40, x + 95, 54, 0xFF111111);
            context.fill(x - 3, 53, x + 95, 54, rainbow);
            context.drawTextWithShadow(this.textRenderer, "§l" + cat, x + 5, 42, 0xFFFFFF);

            int y = 58;
            for (Module m : ModuleManager.modules) {
                if (m.category.equalsIgnoreCase(cat)) {
                    if (!filter.isEmpty() && !m.name.toLowerCase().contains(filter)) continue;

                    boolean hover = mouseX >= x && mouseX <= x + 92 && mouseY >= y && mouseY <= y + 13;
                    if(hover) lastHoveredDesc = "Modul: §b" + m.name + " §7| Click to Toggle";

                    // --- BUTON MODULE ---
                    // Dacă e pornit, îi dăm un "Glow" (fundal mai luminos)
                    int alpha = m.enabled ? 0x77 : 0x33;
                    int colorRGB = m.enabled ? (rainbow & 0x00FFFFFF) : 0x444444;
                    int finalColor = (alpha << 24) | colorRGB;

                    context.fill(x, y, x + 92, y + 13, finalColor);
                    
                    // Textul (Dacă e pornit e Alb, dacă nu e Gri)
                    String label = (bindingModule == m) ? "§f> BIND <" : (m.enabled ? "§f" + m.name : "§8" + m.name);
                    context.drawTextWithShadow(this.textRenderer, label, x + 5, y + 3, 0xFFFFFF);

                    y += 15;
                }
            }
            x += 105;
        }

        // --- BOTTOM BAR INFO ---
        context.fill(0, this.height - 20, this.width, this.height, 0xCC000000);
        context.fill(0, this.height - 21, this.width, this.height - 20, rainbow);
        context.drawTextWithShadow(this.textRenderer, lastHoveredDesc, 10, this.height - 14, 0xFFFFFF);

        // --- HUD LIST (DREAPTA SUS) ---
        renderHUD(context);
    }

    private void renderHUD(DrawContext context) {
        int yOffset = 5;
        for (Module m : ModuleManager.modules) {
            if (m.enabled) {
                int c = Color.HSBtoRGB((System.currentTimeMillis() + (yOffset * 150)) % 5000 / 5000f, 0.6f, 1f);
                String name = m.name.toLowerCase().replace(" ", "");
                context.drawTextWithShadow(this.textRenderer, name, this.width - this.textRenderer.getWidth(name) - 5, yOffset, c);
                yOffset += 10;
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.searchBar.mouseClicked(mouseX, mouseY, button)) return true;

        int x = 15;
        for (String cat : new String[]{"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"}) {
            int y = 58;
            for (Module m : ModuleManager.modules) {
                if (m.category.equalsIgnoreCase(cat)) {
                    if (!searchBar.getText().isEmpty() && !m.name.toLowerCase().contains(searchBar.getText().toLowerCase())) continue;
                    if (mouseX >= x && mouseX <= x + 92 && mouseY >= y && mouseY <= y + 13) {
                        if (button == 0) m.toggle();
                        else if (button == 1) bindingModule = m;
                        return true;
                    }
                    y += 15;
                }
            }
            x += 105;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (bindingModule != null) {
            bindingModule.keyCode = (keyCode == GLFW.GLFW_KEY_ESCAPE) ? 0 : keyCode;
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

    @Override public boolean charTyped(char chr, int modifiers) { return this.searchBar.charTyped(chr, modifiers); }
    @Override public boolean shouldPause() { return false; }
}
