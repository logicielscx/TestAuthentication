package com.example.playerdistancehud;

import java.util.Optional;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Finds the nearest other player that is currently present in the client world.
 */
public final class NearestPlayerFinder {
    private NearestPlayerFinder() {
    }

    public static Optional<PlayerEntity> findNearest(ClientWorld world, PlayerEntity localPlayer) {
        PlayerEntity nearest = null;
        double nearestSquaredDistance = Double.POSITIVE_INFINITY;

        for (PlayerEntity candidate : world.getPlayers()) {
            if (candidate == localPlayer || candidate.isRemoved()) {
                continue;
            }

            double dx = candidate.getX() - localPlayer.getX();
            double dy = candidate.getY() - localPlayer.getY();
            double dz = candidate.getZ() - localPlayer.getZ();
            double squaredDistance = dx * dx + dy * dy + dz * dz;

            if (squaredDistance < nearestSquaredDistance) {
                nearestSquaredDistance = squaredDistance;
                nearest = candidate;
            }
        }

        return Optional.ofNullable(nearest);
    }
}
