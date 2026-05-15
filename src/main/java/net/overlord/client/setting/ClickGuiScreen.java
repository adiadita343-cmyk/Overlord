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
        super(Text.literal("Overlord God"));
    }

    @Override
    protected void init() {
        // Search Bar Ultra-Minimalist
        this.searchBar = new TextFieldWidget(this.textRenderer, this.width / 2 - 70, 8, 140, 14, Text.literal(""));
        this.searchBar.setPlaceholder(Text.literal("🔍 Command..."));
        this.searchBar.setDrawsBackground(false);
        this.addSelectableChild(this.searchBar);
        this.setInitialFocus(this.searchBar);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // ZERO BLUR - Fundal negru cinematic
        context.fill(0, 0, this.width, this.height, 0x77000000);

        int rainbow = Color.HSBtoRGB((System.currentTimeMillis() % 6000) / 6000f, 0.8f, 1f);
        
        // Randare Search Bar
        context.fill(this.width / 2 - 75, 24, this.width / 2 + 75, 25, rainbow);
        this.searchBar.render(context, mouseX, mouseY, delta);

        int x = 20;
        String[] categories = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        String filter = searchBar.getText().toLowerCase();

        for (String cat : categories) {
            // Header Neon
            context.fill(x - 2, 35, x + 92, 48, 0xFF0A0A0A);
            context.fill(x - 2, 47, x + 92, 48, rainbow);
            context.drawTextWithShadow(this.textRenderer, cat, x + 4, 37, 0xFFFFFF);

            int y = 52;
            for (Module m : ModuleManager.modules) {
                if (m.category.equalsIgnoreCase(cat)) {
                    if (!filter.isEmpty() && !m.name.toLowerCase().contains(filter)) continue;

                    boolean hover = mouseX >= x && mouseX <= x + 90 && mouseY >= y && mouseY <= y + 12;
                    
                    // Design Butoane Overlord
                    int bg = m.enabled ? (rainbow & 0x66FFFFFF) | 0x88000000 : (hover ? 0xAA333333 : 0x88111111);
                    context.fill(x, y, x + 90, y + 12, bg);

                    String label = (bindingModule == m) ? "§bBINDING..." : (m.enabled ? "§f" + m.name : "§7" + m.name);
                    context.drawTextWithShadow(this.textRenderer, label, x + 4, y + 2, 0xFFFFFF);
                    y += 14;
                }
            }
            x += 105;
        }

        // HUD - Lista Rainbow în timp real
        int hY = 5;
        for (Module m : ModuleManager.modules) {
            if (m.enabled) {
                int c = Color.HSBtoRGB((System.currentTimeMillis() + (hY * 150)) % 6000 / 6000f, 0.7f, 1f);
                context.drawTextWithShadow(this.textRenderer, m.name.toLowerCase(), this.width - this.textRenderer.getWidth(m.name.toLowerCase()) - 5, hY, c);
                hY += 10;
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.searchBar.mouseClicked(mouseX, mouseY, button)) return true;
        int x = 20;
        for (String cat : new String[]{"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"}) {
            int y = 52;
            for (Module m : ModuleManager.modules) {
                if (m.category.equalsIgnoreCase(cat)) {
                    if (!searchBar.getText().isEmpty() && !m.name.toLowerCase().contains(searchBar.getText().toLowerCase())) continue;
                    if (mouseX >= x && mouseX <= x + 90 && mouseY >= y && mouseY <= y + 12) {
                        if (button == 0) m.toggle();
                        else if (button == 1) bindingModule = m;
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
