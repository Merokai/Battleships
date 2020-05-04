package org.ipi.battleships;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTest {


    @Test
    void shipTests() {
        Ship ship = new Ship(ShipModel.CARRIER, new Coordinate(2, 3), Orientation.EAST);
        assertTrue(ship.isOnCoordinate(2, 3));
    }
}
