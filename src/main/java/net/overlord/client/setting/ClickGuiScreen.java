package com.adiadita343;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class ClickGuiScreen extends Screen {
    public ClickGuiScreen() {
        super(Text.literal("Overlord"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        int x = 20;
        String[] cats = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        for (String cat : cats) {
            context.fill(x, 20, x + 90, 35, 0xFF111111);
            context.drawTextWithShadow(this.textRenderer, cat, x + 5, 24, 0xFF55FF55);
            int y = 40;
            for (Module m : ModuleManager.getModulesByCategory(cat).stream().limit(20).toList()) {
                context.fill(x, y, x + 90, y + 12, m.enabled ? 0xFF2244AA : 0xFF333333);
                context.drawTextWithShadow(this.textRenderer, m.name, x + 4, y + 2, 0xFFFFFFFF);
                y += 14;
            }
            x += 100;
        }
        super.render(context, mouseX, mouseY, delta);
    }
}
