package com.adiadita343.overlord.mixin;

import com.adiadita343.overlord.event.EventBus;
import com.adiadita343.overlord.event.TickEvent;
import com.adiadita343.overlord.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTickInject(CallbackInfo info) {
        // 1. Trimitem tick-ul prin EventBus către toate modulele stil Meteor
        EventBus.INSTANCE.post(TickEvent.INSTANCE);
        
        // 2. Rulăm și managerul global pentru siguranță și verificări de tick standard
        ModuleManager.onTick();
    }
}
