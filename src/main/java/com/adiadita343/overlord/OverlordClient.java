package com.adiadita343.overlord;

import com.adiadita343.overlord.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;

public class OverlordClient implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        // Aici pornim motorul suprem! Inițializăm ModuleManager-ul stil Meteor
        ModuleManager.init();
        
        System.out.println("[Overlord] Clientul a pornit cu succes pe 1.21.1!");
    }
}
