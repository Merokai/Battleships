package org.ipi.battleships;

import org.ipi.battleships.core.entities.Coordinate;
import org.ipi.battleships.core.entities.Fleet;
import org.ipi.battleships.core.entities.Ship;
import org.ipi.battleships.core.enums.Orientation;
import org.ipi.battleships.core.enums.ShipModel;
import org.ipi.battleships.core.enums.ShotResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FleetTest {

    @Test
    void fleetTests() {
        Ship carrier = new Ship(ShipModel.CARRIER, new Coordinate(1, 2), Orientation.SOUTH);
        Ship battleship = new Ship(ShipModel.BATTLESHIP, new Coordinate(2, 2), Orientation.SOUTH);
        Ship cruiser = new Ship(ShipModel.CRUISER, new Coordinate(3, 2), Orientation.SOUTH);
        Ship submarine = new Ship(ShipModel.SUBMARINE, new Coordinate(4, 2), Orientation.SOUTH);
        Ship destroyer = new Ship(ShipModel.DESTROYER, new Coordinate(5, 2), Orientation.SOUTH);

        Fleet fleet = new Fleet(carrier, battleship, cruiser, submarine, destroyer);

        assertEquals(5, fleet.shipsRemainingCount());

        assertThrows(IllegalArgumentException.class, () -> new Fleet(carrier, carrier, carrier, carrier, carrier));

        Ship overlappingCarrier = new Ship(ShipModel.CARRIER, new Coordinate(2, 2), Orientation.SOUTH);

        assertThrows(IllegalArgumentException.class, () -> new Fleet(overlappingCarrier, battleship, cruiser, submarine, destroyer));
    }

    @Test
    void shootTests() {
        Ship carrier = new Ship(ShipModel.CARRIER, new Coordinate(1, 2), Orientation.SOUTH);
        Ship battleship = new Ship(ShipModel.BATTLESHIP, new Coordinate(2, 2), Orientation.SOUTH);
        Ship cruiser = new Ship(ShipModel.CRUISER, new Coordinate(3, 2), Orientation.SOUTH);
        Ship submarine = new Ship(ShipModel.SUBMARINE, new Coordinate(4, 2), Orientation.SOUTH);
        Ship destroyer = new Ship(ShipModel.DESTROYER, new Coordinate(5, 2), Orientation.SOUTH);

        Fleet fleet = new Fleet(carrier, battleship, cruiser, submarine, destroyer);

        assertEquals(ShotResult.HIT, fleet.shootAtCoordinate(new Coordinate(1, 2)));
        assertEquals(ShotResult.MISSED, fleet.shootAtCoordinate(new Coordinate(1, 1)));

        assertEquals(ShotResult.HIT, fleet.shootAtCoordinate(new Coordinate(4, 2)));
        assertEquals(ShotResult.HIT, fleet.shootAtCoordinate(new Coordinate(4, 3)));
        assertEquals(ShotResult.SANK, fleet.shootAtCoordinate(new Coordinate(4, 4)));

    }

    @Test
    void shipsRemainingTests() {
        Ship carrier = new Ship(ShipModel.CARRIER, new Coordinate(1, 2), Orientation.SOUTH);
        Ship battleship = new Ship(ShipModel.BATTLESHIP, new Coordinate(2, 2), Orientation.SOUTH);
        Ship cruiser = new Ship(ShipModel.CRUISER, new Coordinate(3, 2), Orientation.SOUTH);
        Ship submarine = new Ship(ShipModel.SUBMARINE, new Coordinate(4, 2), Orientation.SOUTH);
        Ship destroyer = new Ship(ShipModel.DESTROYER, new Coordinate(5, 2), Orientation.SOUTH);

        Fleet fleet = new Fleet(carrier, battleship, cruiser, submarine, destroyer);

        assertEquals(5, fleet.shipsRemainingCount());
        destroyer.hit(new Coordinate(5, 2));
        // Destroyer sank
        destroyer.hit(new Coordinate(5, 3));
        assertEquals(4, fleet.shipsRemainingCount());
    }
}
