package net.overlord.adiadita343.setting; 

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import java.awt.Color;
import java.util.List;

// IMPORTURILE SUPREME CA SĂ ȚI SE FACĂ VERDE BUILD-UL:
import net.overlord.adiadita343.module.Module;
import net.overlord.adiadita343.module.ModuleManager;

public class ClickGuiScreen extends Screen {
    private TextFieldWidget searchBar;
    private Module bindingModule = null;
    private String tooltip = "Overlord Client | Galactic Edition";

    public ClickGuiScreen() {
        super(Text.literal("Overlord Apex"));
    }

    @Override
    protected void init() {
        this.searchBar = new TextFieldWidget(this.textRenderer, this.width / 2 - 90, 15, 180, 16, Text.literal(""));
        this.searchBar.setPlaceholder(Text.literal("🔍 Explore the universe..."));
        this.searchBar.setDrawsBackground(false); 
        this.addSelectableChild(this.searchBar);
        this.setInitialFocus(this.searchBar);
        this.searchBar.setFocused(true);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, this.width, this.height, 0x55000000);
        int rainbow = Color.HSBtoRGB((System.currentTimeMillis() % 6000) / 6000f, 0.8f, 1f);

        context.fill(this.width / 2 - 95, 10, this.width / 2 + 95, 35, 0xAA0A0A0A);
        context.fill(this.width / 2 - 95, 34, this.width / 2 + 95, 35, rainbow);
        this.searchBar.render(context, mouseX, mouseY, delta);

        int x = 20;
        String[] categories = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        String filter = searchBar.getText();

        for (String cat : categories) {
            context.fill(x - 4, 45, x + 104, 60, 0xDD151515);
            context.fill(x - 4, 59, x + 104, 60, rainbow);
            context.drawTextWithShadow(this.textRenderer, "§l" + cat, x + 6, 48, 0xFFFFFF);

            List<Module> filteredModules = ModuleManager.getSearchQuery(filter);
            
            int y = 64;
            int count = 0;
            for (Module m : filteredModules) {
                if (m.category.equalsIgnoreCase(cat)) {
                    if (count > 22) break;

                    boolean isHovered = mouseX >= x && mouseX <= x + 100 && mouseY >= y && mouseY <= y + 14;
                    if (isHovered) tooltip = "§7Modul: §f" + m.name + " §8| §7Category: §b" + m.category;

                    int btnAlpha = m.enabled ? 0x99 : (isHovered ? 0x66 : 0x44);
                    int btnColor = (btnAlpha << 24) | (m.enabled ? (rainbow & 0x00FFFFFF) : 0x222222);
                    context.fill(x, y, x + 100, y + 13, btnColor);

                    String label = (bindingModule == m) ? "§bBINDING..." : (m.enabled ? "§f" + m.name : "§7" + m.name);
                    context.drawTextWithShadow(this.textRenderer, label, x + 4, y + 3, 0xFFFFFF);

                    y += 16;
                    count++;
                }
            }
            x += 115;
        }

        context.fill(0, this.height - 20, this.width, this.height, 0xCC050505);
        context.fill(0, this.height - 21, this.width, this.height - 20, rainbow);
        context.drawTextWithShadow(this.textRenderer, tooltip, 10, this.height - 14, 0xFFFFFF);

        renderHUD(context);
    }

    private void renderHUD(DrawContext context) {
        int y = 5;
        for (Module m : ModuleManager.getEnabledModules()) {
            if (!m.name.contains("_")) { 
                int color = Color.HSBtoRGB((System.currentTimeMillis() + (y * 120)) % 6000 / 6000f, 0.7f, 1f);
                context.drawTextWithShadow(this.textRenderer, m.name.toLowerCase(), this.width - this.textRenderer.getWidth(m.name) - 5, y, color);
                y += 10;
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.searchBar.mouseClicked(mouseX, mouseY, button)) return true;

        int x = 20;
        String filter = searchBar.getText();
        for (String cat : new String[]{"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"}) {
            int y = 64;
            int count = 0;
            for (Module m : ModuleManager.getSearchQuery(filter)) {
                if (m.category.equalsIgnoreCase(cat)) {
                    if (count > 22) break;
                    if (mouseX >= x && mouseX <= x + 100 && mouseY >= y && mouseY <= y + 13) {
                        if (button == 0) m.toggle();
                        else if (button == 1) bindingModule = m;
                        return true;
                    }
                    y += 16;
                    count++;
                }
            }
            x += 115;
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
        return this.searchBar.charTyped(chr, modifiers);
    }

    @Override public boolean shouldPause() { return false; }
}
