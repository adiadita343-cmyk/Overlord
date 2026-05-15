package com.adiadita343;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class ClickGuiScreen extends Screen {
    private String searchText = "";

    public ClickGuiScreen() {
        super(Text.literal("Overlord Search GUI"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);

        // --- Search Bar ---
        context.fill(20, 5, 200, 20, 0xDD000000); // Fundal bară
        context.drawTextWithShadow(this.textRenderer, "Search: " + searchText + "_", 25, 8, 0xFF55FF55);

        int x = 20;
        String[] cats = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        
        for (String cat : cats) {
            // Desenăm titlul categoriei
            context.fill(x, 25, x + 85, 40, 0xFF111111);
            context.drawTextWithShadow(this.textRenderer, cat, x + 5, 28, 0xFFFFFFFF);

            int y = 45;
            // Filtrăm modulele după ce scrii în Search Bar
            var filteredModules = ModuleManager.getModulesByCategory(cat).stream()
                    .filter(m -> m.name.toLowerCase().contains(searchText.toLowerCase()))
                    .limit(15) // Limităm la 15 pe coloană să nu iasă din ecran
                    .toList();

            for (Module m : filteredModules) {
                context.fill(x, y, x + 85, y + 12, m.enabled ? 0xFF2244AA : 0xFF333333);
                context.drawTextWithShadow(this.textRenderer, m.name, x + 4, y + 2, -1);
                y += 14;
            }
            x += 95;
        }
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        searchText += chr; // Adaugă litera tastată în search
        return true;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_BACKSPACE && searchText.length() > 0) {
            searchText = searchText.substring(0, searchText.length() - 1); // Șterge ultima literă
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
