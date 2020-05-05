package org.ipi.battleships;

import org.junit.jupiter.api.Assertions;
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
    void hitTests(){
        Ship ship = new Ship(ShipModel.CARRIER, new Coordinate(2, 3), Orientation.EAST);

        // Miss (ship is not at coordinate)
        assertEquals(ShootResult.MISSED, ship.hit( new Coordinate(1, 3)));

        // Hit
        assertEquals(ShootResult.HIT, ship.hit( new Coordinate(2, 3)));
        assertEquals(ShootResult.HIT, ship.hit( new Coordinate(3, 3)));
        assertEquals(ShootResult.HIT, ship.hit( new Coordinate(4, 3)));
        assertEquals(ShootResult.HIT, ship.hit( new Coordinate(5, 3)));

        // Miss (coordinate as already been hit)
        assertEquals(ShootResult.MISSED, ship.hit( new Coordinate(5, 3)));

        // Sink
        assertEquals(ShootResult.SANK, ship.hit( new Coordinate(6, 3)));
    }
}
