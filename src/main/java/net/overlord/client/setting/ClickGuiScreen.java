package net.overlord.adiadita343.setting;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import java.util.List;

import net.overlord.adiadita343.module.Module;
import net.overlord.adiadita343.module.ModuleManager;

public class ClickGuiScreen extends Screen {
    private TextFieldWidget searchBar;
    private Module bindingModule = null;
    private String tooltip = "Overlord Elite | Meteor Engine";

    // Culori fixe în format Hex (ARGB): Meteor Dark Style
    private final int BG_COLOR = 0x660B0B0C;       // Fundal general închis amortizat
    private final int CORE_DARK = 0xEE111112;      // Panou interior module
    private final int ACCENT_PURPLE = 0xFF8A2BE2;  // Mov Electric când e ENABLED (Neon Purple)
    private final int TEXT_ENABLED = 0xFFFFFFFF;   // Text alb curat când e activ
    private final int TEXT_DISABLED = 0xFF7A7A7D;  // Text gri industrial când e inactiv
    private final int TEXT_HOVER = 0xFFBF80FF;     // Text mov pal la mouse hover

    public ClickGuiScreen() {
        super(Text.literal("Overlord Engine"));
    }

    @Override
    protected void init() {
        // Bara de căutare plutitoare, stil minimalist Meteor
        this.searchBar = new TextFieldWidget(this.textRenderer, this.width / 2 - 90, 15, 180, 16, Text.literal(""));
        this.searchBar.setPlaceholder(Text.literal("🔍 Search modules..."));
        this.searchBar.setDrawsBackground(false);
        this.addSelectableChild(this.searchBar);
        this.setInitialFocus(this.searchBar);
        this.searchBar.setFocused(true);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // 1. Fundal întunecat curat, fără blur enervant
        context.fill(0, 0, this.width, this.height, BG_COLOR);

        // 2. Randare fundal search bar (Carbon Glass Design)
        context.fill(this.width / 2 - 95, 10, this.width / 2 + 95, 34, CORE_DARK);
        context.fill(this.width / 2 - 95, 33, this.width / 2 + 95, 34, ACCENT_PURPLE);
        this.searchBar.render(context, mouseX, mouseY, delta);

        int x = 20;
        String[] categories = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"};
        String filter = searchBar.getText();

        for (String cat : categories) {
            // 3. Cadru Categorie (Meniu tip Meteor)
            context.fill(x - 4, 45, x + 104, 58, CORE_DARK);
            context.fill(x - 4, 57, x + 104, 58, ACCENT_PURPLE);
            context.drawTextWithShadow(this.textRenderer, cat, x + 6, 47, TEXT_ENABLED);

            List<Module> filteredModules = ModuleManager.getSearchQuery(filter);
            
            int y = 62;
            int count = 0;
            for (Module m : filteredModules) {
                if (m.category.equalsIgnoreCase(cat)) {
                    if (count > 22) break; // Siguranță ecran

                    boolean isHovered = mouseX >= x && mouseX <= x + 100 && mouseY >= y && mouseY <= y + 13;
                    if (isHovered) tooltip = "Module: " + m.name + " | Category: " + m.category;

                    // 4. Logica culorilor: Când e pornit devine complet Mov Electric solid. Altfel e gri/negru.
                    int btnColor = m.enabled ? ACCENT_PURPLE : (isHovered ? 0x2A2A2C : 0x18181A);
                    int textColor = m.enabled ? TEXT_ENABLED : (isHovered ? TEXT_HOVER : TEXT_DISABLED);
                    
                    // Desenăm butonul
                    context.fill(x, y, x + 100, y + 12, (0xFF << 24) | btnColor);

                    // Randare text
                    String label = (bindingModule == m) ? "...Binds" : m.name;
                    context.drawTextWithShadow(this.textRenderer, label, x + 4, y + 2, textColor);

                    y += 15;
                    count++;
                }
            }
            x += 115;
        }

        // 5. Bara inferioară de stare / Informații
        context.fill(0, this.height - 18, this.width, this.height, 0xFF0D0D0E);
        context.fill(0, this.height - 19, this.width, this.height - 18, ACCENT_PURPLE);
        context.drawTextWithShadow(this.textRenderer, tooltip, 10, this.height - 13, TEXT_DISABLED);

        // HUD-ul VECHI A FOST ȘTERS DE AICI (Nu mai există apelul renderHUD)
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.searchBar.mouseClicked(mouseX, mouseY, button)) return true;

        int x = 20;
        String filter = searchBar.getText();
        for (String cat : new String[]{"COMBAT", "MOVEMENT", "VISUAL", "PLAYER", "MISC"}) {
            int y = 62;
            int count = 0;
            for (Module m : ModuleManager.getSearchQuery(filter)) {
                if (m.category.equalsIgnoreCase(cat)) {
                    if (count > 22) break;
                    if (mouseX >= x && mouseX <= x + 100 && mouseY >= y && mouseY <= y + 12) {
                        if (button == 0) m.toggle(); // Click Stânga pornește/oprește
                        else if (button == 1) bindingModule = m; // Click Dreapta pune bind
                        return true;
                    }
                    y += 15;
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
