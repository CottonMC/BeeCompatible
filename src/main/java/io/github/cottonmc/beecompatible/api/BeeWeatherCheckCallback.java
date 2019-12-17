package io.github.cottonmc.beecompatible.api;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.world.World;

public interface BeeWeatherCheckCallback {
	Event<BeeWeatherCheckCallback> EVENT = EventFactory.createArrayBacked(BeeWeatherCheckCallback.class,
			(listeners) -> (world, bee) -> {
				boolean reject = false;
				for (BeeWeatherCheckCallback event : listeners) {
					TriState result = event.checkWeather(world, bee);
					if (result.get()) return TriState.TRUE;
					if (result == TriState.FALSE) reject = true;
				}
				return reject? TriState.FALSE : TriState.DEFAULT;
			});

	/**
	 * Check whether this bee is allowed to go out with the current weather.
	 * @param world The world to check weather on.
	 * @param bee The bee to check for.
	 * @return whether the bee may exit at the given weather. True to forcibly allow, false to forcibly reject, default to let other events or vanilla decide.
	 * Note: a true return will override any false returns.
	 */
	TriState checkWeather(World world, BeeEntity bee);
}
