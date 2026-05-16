package com.adiadita343.overlord.module;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;

public class ClickGuiScreen extends Screen {
    
    // Categoriile pe care le avem în registre
    private final String[] categorii = {"COMBAT", "MOVEMENT", "VISUAL", "PLAYER"};
    
    // Culorile stil Meteor (Mov închis și gri închis premium)
    private final int CULOARE_PANEL = new Color(25, 25, 35, 230).getRGB();       // Fundal panel mov foarte închis
    private final int CULOARE_ACCENT = new Color(135, 80, 220, 255).getRGB();    // Mov Meteor intens pentru module active
    private final int CULOARE_MODUL_OFF = new Color(45, 45, 55, 255).getRGB();  // Gri-mov închis pentru module oprite
    private final int CULOARE_TEXT = new Color(255, 255, 255, 255).getRGB();     // Alb curat pentru text

    public ClickGuiScreen() {
        super(Text.literal("Overlord ClickGUI"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // 1. Randerăm fundalul blurat clasic din Minecraft când deschizi un meniu
        super.render(context, mouseX, mouseY, delta);
        
        int xStart = 20; // Poziția de start pe axa X pentru primul panel
        int latimePanel = 110;
        int inaltimeSlot = 14;

        // 2. Trecem prin fiecare categorie și îi creăm un panel dedicat
        for (String cat : categorii) {
            int yStart = 30;

            // Desenăm header-ul categoriei (Caseta mov de sus)
            context.fill(xStart, yStart, xStart + latimePanel, yStart + 16, CULOARE_ACCENT);
            context.drawText(this.textRenderer, cat, xStart + 5, yStart + 4, CULOARE_TEXT, false);
            yStart += 18;

            // Filtrăm modulele care aparțin acestei categorii din cele 450+ încărcate
            List<Module> moduleCategorie = ModuleManager.modules.stream()
                    .filter(m -> m.category.equalsIgnoreCase(cat))
                    .collect(Collectors.toList());

            // Randerăm butoanele pentru fiecare modul în parte
            for (Module m : moduleCategorie) {
                // Dacă modulul e pornit e Mov Meteor, dacă e oprit e Gri închis
                int culoareButon = m.enabled ? CULOARE_ACCENT : CULOARE_MODUL_OFF;
                
                // Desenăm fundalul butonului
                context.fill(xStart, yStart, xStart + latimePanel, yStart + inaltimeSlot, culoareButon);
                
                // Desenăm numele modulului text
                context.drawText(this.textRenderer, m.name, xStart + 6, yStart + 3, CULOARE_TEXT, false);
                
                yStart += inaltimeSlot + 2; // Spațiere între butoane
            }

            xStart += latimePanel + 15; // Trecem la următorul panel din dreapta
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int xStart = 20;
        int latimePanel = 110;
        int inaltimeSlot = 14;

        // Prindem click-urile pe butoane ca să dăm ENABLE / DISABLE real prin EventBus
        for (String cat : categorii) {
            int yStart = 30 + 18;

            List<Module> moduleCategorie = ModuleManager.modules.stream()
                    .filter(m -> m.category.equalsIgnoreCase(cat))
                    .collect(Collectors.toList());

            for (Module m : moduleCategorie) {
                // Verificăm dacă mouse-ul a dat click exact în interiorul căsuței modului
                if (mouseX >= xStart && mouseX <= xStart + latimePanel && mouseY >= yStart && mouseY <= yStart + inaltimeSlot) {
                    m.toggle(); // Rulează codul real de onEnable() / onDisable() stil Meteor!
                    if (this.client != null && this.client.player != null) {
                        this.client.player.playSound(net.minecraft.sound.SoundEvents.UI_BUTTON_CLICK.value(), 0.3F, 1.0F);
                    }
                    return true;
                }
                yStart += inaltimeSlot + 2;
            }
            xStart += latimePanel + 15;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() {
        return false; // Jocul nu se oprește în Singleplayer când deschizi meniul, exact ca pe servere
    }
}
