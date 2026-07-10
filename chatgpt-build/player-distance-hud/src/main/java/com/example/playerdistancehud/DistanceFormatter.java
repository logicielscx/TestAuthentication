package com.example.playerdistancehud;

import java.util.Locale;

import net.minecraft.entity.Entity;

/**
 * Calculates and formats exact three-dimensional entity distances.
 */
public final class DistanceFormatter {
    private DistanceFormatter() {
    }

    /**
     * Calculates Euclidean distance from the entities' current, unrounded positions.
     */
    public static double calculateDistance(Entity first, Entity second) {
        return calculateDistance(
                first.getX(), first.getY(), first.getZ(),
                second.getX(), second.getY(), second.getZ()
        );
    }

    /**
     * Calculates sqrt(dx^2 + dy^2 + dz^2) for independently testable coordinates.
     */
    public static double calculateDistance(
            double firstX,
            double firstY,
            double firstZ,
            double secondX,
            double secondY,
            double secondZ
    ) {
        double dx = secondX - firstX;
        double dy = secondY - firstY;
        double dz = secondZ - firstZ;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    /**
     * Formats a distance with exactly one decimal place and a stable decimal separator.
     */
    public static String format(double distance) {
        return String.format(Locale.ROOT, "%.1f", distance);
    }
}
