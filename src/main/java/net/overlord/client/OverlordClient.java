package net.overlord.client; // Șterge ".setting" de aici

import net.fabricmc.api.ClientModInitializer;

public class OverlordClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("Overlord Client a pornit cu succes!");
    }
}