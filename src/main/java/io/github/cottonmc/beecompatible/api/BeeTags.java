package io.github.cottonmc.beecompatible.api;

import io.github.cottonmc.beecompatible.BeeCompatible;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class BeeTags {
	/**
	 * If an item is in this tag, bees will follow players who hold said item.
	 * Allows addition of new tempting items that shouldn't be in the #minecraft:flowers tag.
	 */
	public static final Tag<Item> BEE_TEMPTING = TagRegistry.item(new Identifier(BeeCompatible.MODID, "bee_tempting"));

	/**
	 * If a block is in this tag, bees will be able to collect nectar from said block.
	 * Allows addition of new feeding blocks that shouldn't be in the #minecraft:flowers tag.
	 */
	public static final Tag<Block> BEE_FEEDING = TagRegistry.block(new Identifier(BeeCompatible.MODID, "bee_feeding"));

	public static void init() {

	}
}
