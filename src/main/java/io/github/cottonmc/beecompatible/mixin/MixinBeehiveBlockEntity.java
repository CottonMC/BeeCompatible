package io.github.cottonmc.beecompatible.mixin;

import io.github.cottonmc.beecompatible.api.BeeTimeCheckCallback;
import io.github.cottonmc.beecompatible.api.BeeWeatherCheckCallback;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(BeehiveBlockEntity.class)
public class MixinBeehiveBlockEntity {

	@Redirect(method = "releaseBee", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isNight()Z"))
	private boolean timeEvent(World world, BlockState state, CompoundTag tag, List<Entity> entities, BeehiveBlockEntity.BeeState beeState) {
		Entity entity = EntityType.loadEntityWithPassengers(tag, world, e -> e);
		if (entity instanceof BeeEntity) {
			BeeEntity bee = (BeeEntity)entity;
			TriState result = BeeTimeCheckCallback.EVENT.invoker().checkTime(world, bee);
			if (result.get()) return false; //a negative here allows bees to exit
			if (result == TriState.DEFAULT) return world.isNight();
		}
		return true;
	}

	@Redirect(method = "releaseBee", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isRaining()Z"))
	private boolean weatherEvent(World world, BlockState state, CompoundTag tag, List<Entity> entities, BeehiveBlockEntity.BeeState beeState) {
		Entity entity = EntityType.loadEntityWithPassengers(tag, world, e -> e);
		if (entity instanceof BeeEntity) {
			BeeEntity bee = (BeeEntity)entity;
			TriState result = BeeWeatherCheckCallback.EVENT.invoker().checkWeather(world, bee);
			if (result.get()) return false; //a negative here allows bees to exit
			if (result == TriState.DEFAULT) return world.isRaining();
		}
		return true;
	}
}
