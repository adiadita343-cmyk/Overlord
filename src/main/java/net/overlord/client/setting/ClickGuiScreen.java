package com.adiadita343;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class ClickGUI extends Screen {
    public ClickGUI() {
        super(Text.literal("Overlord Client"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        int x = 20;
        String[] cats = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};

        for (String cat : cats) {
            context.fill(x, 20, x + 90, 35, 0xFF222222);
            context.drawTextWithShadow(textRenderer, cat, x + 5, 24, 0xFFFFFF);
            int y = 40;
            for (Module m : ModuleManager.getModulesByCategory(cat).stream().limit(25).toList()) {
                int bgColor = m.enabled ? 0xFF00AA00 : 0xFF333333;
                context.fill(x, y, x + 90, y + 12, bgColor);
                context.drawTextWithShadow(textRenderer, m.name, x + 5, y + 2, 0xFFFFFF);
                y += 14;
            }
            x += 100;
        }
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() { return false; }
}
