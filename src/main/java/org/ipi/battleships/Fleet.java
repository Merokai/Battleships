package org.ipi.battleships;

import java.util.ArrayList;
import java.util.List;

public class Fleet {

    private final List<Ship> ships;

    public Fleet(Ship carrier, Ship battleship, Ship cruiser, Ship submarine, Ship destroyer) {
        ships = new ArrayList<>();
        ships.add(carrier);
        ships.add(battleship);
        ships.add(cruiser);
        ships.add(submarine);
        ships.add(destroyer);

        // Verify whether each ShipModel exists in the ships set
        verifyEveryModelIsPresent();

        // Verify whether no ships overlaps another
        verifyNoShipOverlaps();

    }

    private void verifyNoShipOverlaps() {
        for (int i = 0; i < ships.size(); i++) {
            for (int j = i + 1; j < ships.size(); j++) {
                if (ships.get(i).isOverlapping(ships.get(j))) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    private void verifyEveryModelIsPresent() {
        for (ShipModel model : ShipModel.values()) {
            if (ships.stream().noneMatch((Ship s) -> s.isOfModel(model))) {
                throw new IllegalArgumentException();
            }
        }
    }

    public int shipsRemainingCount() {
        return ships.size();
    }

    public ShootResult shootAtCoordinate(Coordinate c) {
        for (Ship s : ships) {
            ShootResult result = s.hit(c);
            if (result != ShootResult.MISSED) {
                return result;
            }
        }
        return ShootResult.MISSED;
    }
}
