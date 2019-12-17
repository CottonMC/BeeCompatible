package io.github.cottonmc.beecompatible.api;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.world.World;

public interface BeeTimeCheckCallback {
	Event<BeeTimeCheckCallback> EVENT = EventFactory.createArrayBacked(BeeTimeCheckCallback.class,
			(listeners) -> (world, bee) -> {
				for (BeeTimeCheckCallback event : listeners) {
					TriState result = event.checkTime(world, bee);
					if (result != TriState.DEFAULT) return result;
				}
				return TriState.DEFAULT;
			});

	/**
	 * Check whether this bee is allowed to go out with the current time.
	 * @param world The world to check time on.
	 * @param bee The bee to check for.
	 * @return true if the bee may exit the hive at the current time, false if the bee may not, default to fall back to other callbacks
	 */
	TriState checkTime(World world, BeeEntity bee);
}
