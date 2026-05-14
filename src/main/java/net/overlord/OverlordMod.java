package net.overlord;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OverlordMod implements ModInitializer {
	public static final String MOD_ID = "overlord";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Aici am creat item-ul tau!
	public static final Item OVERLORD_ESSENCE = new Item(new Item.Settings());

	@Override
	public void onInitialize() {
		LOGGER.info("Sucessfuly activated!");
		
		// Inregistram item-ul in joc
		Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "overlord_essence"), OVERLORD_ESSENCE);
	}
}
