package com.adiadita343.overlord.module;

import net.minecraft.client.MinecraftClient;

public abstract class Module {
    // Instanța globală de Minecraft pentru ca toate modulele să poată interacționa cu jocul
    public static final MinecraftClient mc = MinecraftClient.getInstance();

    public String name;
    public String category;
    public boolean enabled;
    public int keyCode;

    // =========================================================================
    // SISTEMUL DE SETĂRI INTEGRAT
    // =========================================================================
    // value1: Folosit pentru valori numerice principale (Ex: CPS la AutoClicker, Distanță la Reach)
    public double value1 = 0;    
    // value2: Valoare numerică secundară (Ex: Knockback orizontal/vertical la Velocity)
    public double value2 = 0;    
    // mode: Folosit pentru moduri text (Ex: "LEFT" / "RIGHT" pentru mouse la AutoClicker, "VANILLA" / "PACKET" la Fly)
    public String mode = "NONE"; 

    public Module(String name, String category) {
        this.name = name;
        this.category = category;
        this.enabled = false;
    }

    // Metode executate automat în momentul în care modulul este pornit sau oprit
    public void onEnable() {}
    public void onDisable() {}
    
    // Metoda abstractă care rulează la fiecare tick al jocului (trebuie implementată de fiecare modul în registre)
    public abstract void onTick();
    
    // Metoda opțională folosită pentru randări grafice pe ecran (overlays, ESP, linii)
    public void onRender() {}

    // Funcția principală care schimbă starea modulului (pornit/oprit)
    public void toggle() {
        this.enabled = !this.enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }
}
