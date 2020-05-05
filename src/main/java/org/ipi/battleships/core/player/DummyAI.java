package org.ipi.battleships.core.player;

import org.ipi.battleships.core.entities.Coordinate;
import org.ipi.battleships.core.entities.Fleet;
import org.ipi.battleships.core.entities.Ship;
import org.ipi.battleships.core.enums.Orientation;
import org.ipi.battleships.core.enums.ShipModel;
import org.ipi.battleships.core.enums.ShotResult;

public class DummyAI implements Player {
    // Dummiest AI ever: Just try every coordinates in a [1-10] grid
    private int coordinate = 0;

    @Override
    public Coordinate requestShootCoordinate() {
        return new Coordinate(1 + coordinate % 10, 1 + coordinate++ / 10);
    }

    @Override
    public Coordinate requestShootCoordinate(ShotResult lastShotResult) {
        return requestShootCoordinate();
    }

    @Override
    public Fleet requestFleet() {

        Ship carrier = new Ship(ShipModel.CARRIER, new Coordinate(1, 2), Orientation.SOUTH);
        Ship battleship = new Ship(ShipModel.BATTLESHIP, new Coordinate(2, 2), Orientation.SOUTH);
        Ship cruiser = new Ship(ShipModel.CRUISER, new Coordinate(3, 2), Orientation.SOUTH);
        Ship submarine = new Ship(ShipModel.SUBMARINE, new Coordinate(4, 2), Orientation.SOUTH);
        Ship destroyer = new Ship(ShipModel.DESTROYER, new Coordinate(5, 2), Orientation.SOUTH);

        return new Fleet(carrier, battleship, cruiser, submarine, destroyer);
    }
}
