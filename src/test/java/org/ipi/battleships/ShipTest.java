package org.ipi.battleships;

import org.ipi.battleships.core.entities.Coordinate;
import org.ipi.battleships.core.entities.Ship;
import org.ipi.battleships.core.enums.Orientation;
import org.ipi.battleships.core.enums.ShipModel;
import org.ipi.battleships.core.enums.ShotResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTest {


    @Test
    void shipTests() {
        Ship ship = new Ship(ShipModel.CARRIER, new Coordinate(2, 3), Orientation.EAST);
        assertTrue(ship.isOnCoordinate(new Coordinate(2, 3)));
        assertTrue(ship.isOnCoordinate(new Coordinate(3, 3)));
        assertTrue(ship.isOnCoordinate(new Coordinate(4, 3)));
        assertTrue(ship.isOnCoordinate(new Coordinate(5, 3)));
        assertTrue(ship.isOnCoordinate(new Coordinate(6, 3)));

        assertFalse(ship.isOnCoordinate(new Coordinate(2, 4)));
    }

    @Test
    void hitTests() {
        Ship ship = new Ship(ShipModel.CARRIER, new Coordinate(2, 3), Orientation.EAST);

        // Miss (ship is not at coordinate)
        assertEquals(ShotResult.MISSED, ship.hit(new Coordinate(1, 3)));

        // Hit
        assertEquals(ShotResult.HIT, ship.hit(new Coordinate(2, 3)));
        assertEquals(ShotResult.HIT, ship.hit(new Coordinate(3, 3)));
        assertEquals(ShotResult.HIT, ship.hit(new Coordinate(4, 3)));
        assertEquals(ShotResult.HIT, ship.hit(new Coordinate(5, 3)));

        // Miss (coordinate as already been hit)
        assertEquals(ShotResult.MISSED, ship.hit(new Coordinate(5, 3)));

        // Sink
        assertEquals(ShotResult.SANK, ship.hit(new Coordinate(6, 3)));
    }

    @Test
    void isNotSankTest() {
        Ship ship = new Ship(ShipModel.CARRIER, new Coordinate(2, 3), Orientation.EAST);
        ship.hit(new Coordinate(2, 3));
        assertFalse(ship.sank());
        ship.hit(new Coordinate(3, 3));
        assertFalse(ship.sank());
        ship.hit(new Coordinate(4, 3));
        assertFalse(ship.sank());
        ship.hit(new Coordinate(5, 3));
        assertFalse(ship.sank());
        ship.hit(new Coordinate(6, 3));
        assertTrue(ship.sank());
    }
}
