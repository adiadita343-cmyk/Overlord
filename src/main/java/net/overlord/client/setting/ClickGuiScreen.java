package com.adiadita343;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import java.util.stream.Collectors;

public class ClickGuiScreen extends Screen {
    private TextFieldWidget searchBar;
    private static final int PANEL_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 14;

    public ClickGuiScreen() {
        super(Text.literal("Overlord Ultra"));
    }

    @Override
    protected void init() {
        // Search bar modern - centrat și stilizat
        this.searchBar = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, 15, 200, 20, Text.literal(""));
        this.searchBar.setPlaceholder(Text.literal("🔍 Search Overlord..."));
        this.searchBar.setDrawsBackground(true);
        this.addSelectableChild(this.searchBar);
        this.setInitialFocus(this.searchBar);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Fundal întunecat cu gradient (Arată mult mai pro)
        context.fill(0, 0, this.width, this.height, 0x88050505);

        // Randăm bara de search
        this.searchBar.render(context, mouseX, mouseY, delta);

        String filter = searchBar.getText().toLowerCase();
        int x = 30;
        String[] categories = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};

        for (String cat : categories) {
            // Capul tabelului (Header Categorie)
            context.fill(x - 2, 45, x + PANEL_WIDTH - 2, 58, 0xFF2196F3); // Albastru Overlord
            context.drawTextWithShadow(this.textRenderer, "§l" + cat, x + 5, 48, 0xFFFFFF);

            int y = 60;
            for (Module m : ModuleManager.modules) {
                if (m.category.equalsIgnoreCase(cat)) {
                    // Filtrare inteligentă
                    if (!filter.isEmpty() && !m.name.toLowerCase().contains(filter)) continue;

                    // Hover effect: dacă mouse-ul e peste, se face mai deschis
                    boolean isHovered = mouseX >= x && mouseX <= x + PANEL_WIDTH - 10 && mouseY >= y && mouseY <= y + BUTTON_HEIGHT - 2;
                    int btnColor = m.enabled ? (isHovered ? 0xDD00FF00 : 0xAA00FF00) : (isHovered ? 0xDD444444 : 0xAA222222);

                    // Corpul butonului
                    context.fill(x, y, x + PANEL_WIDTH - 10, y + BUTTON_HEIGHT - 2, btnColor);
                    
                    // Numele modulului
                    context.drawTextWithShadow(this.textRenderer, m.name, x + 4, y + 2, m.enabled ? 0xFFFFFF : 0xBBBBBB);

                    y += BUTTON_HEIGHT;
                }
            }
            x += PANEL_WIDTH + 15;
        }
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.searchBar.mouseClicked(mouseX, mouseY, button)) return true;

        int x = 30;
        String[] categories = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        String filter = searchBar.getText().toLowerCase();

        for (String cat : categories) {
            int y = 60;
            for (Module m : ModuleManager.modules) {
                if (m.category.equalsIgnoreCase(cat)) {
                    if (!filter.isEmpty() && !m.name.toLowerCase().contains(filter)) continue;

                    if (mouseX >= x && mouseX <= x + PANEL_WIDTH - 10 && mouseY >= y && mouseY <= y + BUTTON_HEIGHT - 2) {
                        m.toggle();
                        // Sunet de click pentru feedback (opțional, dar adaugă la experiență)
                        return true;
                    }
                    y += BUTTON_HEIGHT;
                }
            }
            x += PANEL_WIDTH + 15;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.searchBar.keyPressed(keyCode, scanCode, modifiers)) return true;
        
        // Shortcut: Dacă apeși ENTER și ai scris ceva, pornește primul modul găsit
        if (keyCode == GLFW.GLFW_KEY_ENTER && !searchBar.getText().isEmpty()) {
            for (Module m : ModuleManager.modules) {
                if (m.name.toLowerCase().contains(searchBar.getText().toLowerCase())) {
                    m.toggle();
                    this.close();
                    return true;
                }
            }
        }

        if (keyCode == GLFW.GLFW_KEY_ESCAPE || keyCode == GLFW.GLFW_KEY_RIGHT_SHIFT) {
            this.close();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override public boolean charTyped(char chr, int modifiers) { return this.searchBar.charTyped(chr, modifiers); }
    @Override public boolean shouldPause() { return false; }
}
