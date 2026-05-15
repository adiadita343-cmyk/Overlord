package com.adiadita343;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.glfw.GLFW;
import java.awt.Color;

public class ClickGuiScreen extends Screen {
    private TextFieldWidget searchBar;
    private Module bindingModule = null;
    private float animationProgress = 0f; // Pentru animația de deschidere

    public ClickGuiScreen() {
        super(Text.literal("Overlord Apex"));
    }

    @Override
    protected void init() {
        animationProgress = 0f;
        // Search Bar Ultra-Clean (Fără margini urâte)
        this.searchBar = new TextFieldWidget(this.textRenderer, this.width / 2 - 70, 15, 140, 14, Text.literal(""));
        this.searchBar.setPlaceholder(Text.literal("Type to hack..."));
        this.searchBar.setDrawsBackground(false);
        this.addSelectableChild(this.searchBar);
        this.setInitialFocus(this.searchBar);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // 1. Animație de Fade-In (Meniul apare lin)
        animationProgress = MathHelper.lerp(delta * 0.2f, animationProgress, 1.0f);
        
        // Fundal întunecat profesional (Zero Minecraft Blur)
        context.fill(0, 0, this.width, this.height, (int)(0x77 * animationProgress) << 24);

        // 2. Search Bar Design (Minimalist)
        int searchWidth = 160;
        context.fill(this.width / 2 - 85, 12, this.width / 2 + 85, 32, 0x99111111);
        int rainbow = Color.HSBtoRGB((System.currentTimeMillis() % 6000) / 6000f, 0.7f, 1f);
        context.fill(this.width / 2 - 85, 31, this.width / 2 + 85, 32, rainbow); // Bara de jos rainbow
        this.searchBar.render(context, mouseX, mouseY, delta);

        int x = 25;
        String[] categories = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        String filter = searchBar.getText().toLowerCase();

        for (String cat : categories) {
            // 3. Randare Panou Categorie (Premium Look)
            context.fill(x - 3, 45, x + 93, 60, 0xFF151515); // Header Background
            context.fill(x - 3, 59, x + 93, 60, rainbow); // Accent line
            context.drawTextWithShadow(this.textRenderer, "§l" + cat, x + 4, 48, 0xFFFFFF);

            int y = 64;
            for (Module m : ModuleManager.modules) {
                if (m.category.equalsIgnoreCase(cat)) {
                    if (!filter.isEmpty() && !m.name.toLowerCase().contains(filter)) continue;

                    boolean isHovered = mouseX >= x && mouseX <= x + 90 && mouseY >= y && mouseY <= y + 14;
                    
                    // Efect de "Fade" pe butoane
                    int alpha = (int)(0xAA * animationProgress);
                    int btnColor = m.enabled ? (rainbow & 0x00FFFFFF) | (alpha << 24) : (isHovered ? 0xCC333333 : 0x88222222);
                    
                    // Buton cu margini rotunjite (simulat prin fill)
                    context.fill(x, y, x + 90, y + 13, btnColor);

                    // Textul Modulului
                    String name = (bindingModule == m) ? "§7Bind..." : (m.enabled ? "§f" + m.name : "§7" + m.name);
                    context.drawTextWithShadow(this.textRenderer, name, x + 4, y + 3, 0xFFFFFF);

                    // 4. Mici detalii vizuale (Settings icon placeholder)
                    if (isHovered) {
                        context.drawTextWithShadow(this.textRenderer, "...", x + 80, y + 2, 0xFFFFFF);
                    }

                    y += 16;
                }
            }
            x += 105;
        }

        // 5. HUD - Module Active în colț (Real-time)
        renderActiveModules(context);
    }

    private void renderActiveModules(DrawContext context) {
        int yHUD = 5;
        for (Module m : ModuleManager.modules) {
            if (m.enabled) {
                int color = Color.HSBtoRGB((System.currentTimeMillis() + (yHUD * 100)) % 6000 / 6000f, 0.6f, 1f);
                String text = m.name;
                int textWidth = this.textRenderer.getWidth(text);
                context.drawTextWithShadow(this.textRenderer, text, this.width - textWidth - 5, yHUD, color);
                yHUD += 10;
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.searchBar.mouseClicked(mouseX, mouseY, button)) return true;

        int x = 25;
        String[] categories = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        String filter = searchBar.getText().toLowerCase();

        for (String cat : categories) {
            int y = 64;
            for (Module m : ModuleManager.modules) {
                if (m.category.equalsIgnoreCase(cat)) {
                    if (!filter.isEmpty() && !m.name.toLowerCase().contains(filter)) continue;

                    if (mouseX >= x && mouseX <= x + 90 && mouseY >= y && mouseY <= y + 14) {
                        if (button == 0) m.toggle();
                        else if (button == 1) bindingModule = m;
                        return true;
                    }
                    y += 16;
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

    @Override public boolean charTyped(char chr, int modifiers) { return this.searchBar.charTyped(chr, modifiers); }
    @Override public boolean shouldPause() { return false; }
}
