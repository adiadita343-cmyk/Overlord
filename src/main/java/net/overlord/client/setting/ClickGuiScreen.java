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
        // Search Bar - Meteor Style
        this.searchBar = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, 10, 200, 18, Text.literal(""));
        this.searchBar.setPlaceholder(Text.literal("🔍 Search 1100+ modules..."));
        this.searchBar.setDrawsBackground(true);
        this.addSelectableChild(this.searchBar);
        this.setInitialFocus(this.searchBar);
        this.searchBar.setFocused(true);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // ZERO BLUR - Fundal transparent curat
        context.fill(0, 0, this.width, this.height, 0x33000000);

        int rainbow = Color.HSBtoRGB((System.currentTimeMillis() % 4000) / 4000f, 0.8f, 1f);
        this.searchBar.render(context, mouseX, mouseY, delta);

        int x = 10;
        String[] categories = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        String filter = searchBar.getText().toLowerCase();

        for (String cat : categories) {
            // Header Stilizat
            context.fill(x - 2, 35, x + 115, 48, 0xEE111111);
            context.fill(x - 2, 47, x + 115, 48, rainbow);
            context.drawTextWithShadow(this.textRenderer, "§l" + cat, x + 4, 37, 0xFFFFFF);

            int y = 52;
            int count = 0;
            for (Module m : ModuleManager.modules) {
                if (m.category.equalsIgnoreCase(cat)) {
                    if (!filter.isEmpty() && !m.name.toLowerCase().contains(filter)) continue;
                    if (count > 25) break;

                    boolean hover = mouseX >= x && mouseX <= x + 110 && mouseY >= y && mouseY <= y + 12;
                    int bg = m.enabled ? (rainbow & 0x66FFFFFF) | 0xAA000000 : (hover ? 0xAA444444 : 0xAA222222);
                    
                    context.fill(x, y, x + 110, y + 12, bg);
                    
                    String label = (bindingModule == m) ? "§bBIND..." : (m.enabled ? "§f" + m.name : "§7" + m.name);
                    context.drawTextWithShadow(this.textRenderer, label, x + 4, y + 2, 0xFFFFFF);

                    y += 14;
                    count++;
                }
            }
            x += 125;
        }

        // HUD - Module Active în colț
        int hudY = 5;
        for (Module m : ModuleManager.modules) {
            if (m.enabled && !m.name.contains("_")) {
                int c = Color.HSBtoRGB((System.currentTimeMillis() + (hudY * 100)) % 4000 / 4000f, 0.8f, 1f);
                context.drawTextWithShadow(this.textRenderer, m.name.toLowerCase(), this.width - this.textRenderer.getWidth(m.name) - 5, hudY, c);
                hudY += 10;
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.searchBar.mouseClicked(mouseX, mouseY, button)) return true;

        int x = 10;
        for (String cat : new String[]{"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"}) {
            int y = 52;
            int count = 0;
            for (Module m : ModuleManager.modules) {
                if (m.category.equalsIgnoreCase(cat)) {
                    if (!searchBar.getText().isEmpty() && !m.name.toLowerCase().contains(searchBar.getText().toLowerCase())) continue;
                    if (count > 25) break;

                    if (mouseX >= x && mouseX <= x + 110 && mouseY >= y && mouseY <= y + 12) {
                        if (button == 0) m.toggle();
                        else if (button == 1) bindingModule = m;
                        return true;
                    }
                    y += 14;
                    count++;
                }
            }
            x += 125;
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

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (this.searchBar.charTyped(chr, modifiers)) return true;
        return super.charTyped(chr, modifiers);
    }

    @Override public boolean shouldPause() { return false; }
}
