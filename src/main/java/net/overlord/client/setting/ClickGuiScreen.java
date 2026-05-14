package net.overlord.client.setting;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import java.awt.Color;

// Importurile astea vor merge DOAR dacă fișierele sursă au pachetul declarat
import net.overlord.client.module.HackModule;
import net.overlord.client.module.ModuleManager;

public class ClickGuiScreen extends Screen {

    public ClickGuiScreen() {
        super(Text.literal("Overlord ClickGUI"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (this.client == null) return;

        this.renderBackground(context, mouseX, mouseY, delta);

        int xOffset = 20;

        // Randare Categorii
        for (HackModule.Category category : HackModule.Category.values()) {
            context.drawTextWithShadow(this.textRenderer, category.name(), xOffset, 20, Color.ORANGE.getRGB());

            int yOffset = 40;
            // Randare Module
            for (HackModule module : ModuleManager.getModules()) {
                if (module.getCategory() == category) {
                    int color = module.isEnabled() ? Color.GREEN.getRGB() : Color.WHITE.getRGB();
                    context.drawTextWithShadow(this.textRenderer, module.getName(), xOffset, yOffset, color);
                    yOffset += 12;
                }
            }
            xOffset += 100;
        }
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}