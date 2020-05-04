package org.ipi.battleships;

import org.junit.jupiter.api.Test;

public class ShipTest {


    @Test
    void shipTests() {
        Ship ship = new Ship(ShipModel.CARRIER, new Coordinate(2, 3), Orientation.EAST);
    }
}
