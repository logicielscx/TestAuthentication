package com.example.playerdistancehud;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;

import org.junit.jupiter.api.Test;

class DistanceFormatterTest {
    @Test
    void calculatesTrueThreeDimensionalDistance() {
        assertEquals(13.0, DistanceFormatter.calculateDistance(0, 0, 0, 3, 4, 12), 1.0e-12);
    }

    @Test
    void verticalSeparationAffectsDistance() {
        assertEquals(7.5, DistanceFormatter.calculateDistance(10, 2, -4, 10, 9.5, -4), 1.0e-12);
    }

    @Test
    void formatsOneDecimalUsingRootLocale() {
        Locale previous = Locale.getDefault();
        try {
            Locale.setDefault(Locale.GERMANY);
            assertEquals("128.6", DistanceFormatter.format(128.64));
            assertEquals("3.0", DistanceFormatter.format(3));
        } finally {
            Locale.setDefault(previous);
        }
    }
}
