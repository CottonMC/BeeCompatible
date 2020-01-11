package io.github.cottonmc.beecompatible;

import io.github.cottonmc.beecompatible.api.BeeTags;
import net.fabricmc.api.ModInitializer;

public class BeeCompatible implements ModInitializer {
	public static final String MODID = "beecompatible";

	@Override
	public void onInitialize() {
		BeeTags.init();
	}
}
