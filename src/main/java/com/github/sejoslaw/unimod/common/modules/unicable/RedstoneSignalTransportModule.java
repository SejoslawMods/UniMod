package com.github.sejoslaw.unimod.common.modules.unicable;

import java.util.ArrayList;
import java.util.Collection;

import com.github.sejoslaw.unimod.api.modules.IUniCableModule;
import com.github.sejoslaw.unimod.api.tileentities.IUniCable;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

/**
 * @author Sejoslaw - https://github.com/Sejoslaw
 */
public final class RedstoneSignalTransportModule implements IUniCableModule {
	public boolean canConnect(IUniCable cable, Direction direction) {
		World world = cable.getWorld();
		BlockPos pos = cable.getPos().offset(direction);

		return world.getBlockState(pos).emitsRedstonePower();
	}

	public void transmit(IUniCable cable, Direction direction) {
		// TODO: Transmit redstone power without loss.
	}

	public Collection<String> getMessages(IUniCable cable) {
		String message = "Redstone Sources: [ ";

		for (Direction direction : Direction.values()) {
			if (this.canConnect(cable, direction)) {
				message += direction.getName() + " ";
			}
		}

		message += "]";

		Collection<String> messages = new ArrayList<>();
		messages.add(message);

		return messages;
	}
}