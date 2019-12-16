package io.github.cottonmc.beecompatible.api;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.world.World;

public interface BeeWeatherCheckCallback {
	Event<BeeWeatherCheckCallback> EVENT = EventFactory.createArrayBacked(BeeWeatherCheckCallback.class,
			(listeners) -> (world, bee) -> {
				for (BeeWeatherCheckCallback event : listeners) {
					boolean result = event.checkWeather(world, bee);
					if (result) return true;
				}
				return false;
			});

	/**
	 * Check whether this bee is allowed to go out with the current weather.
	 * @param world The world to check weather on.
	 * @param bee The bee to check for.
	 * @return true if the bee may exit the hive at the current weather.
	 */
	boolean checkWeather(World world, BeeEntity bee);
}
