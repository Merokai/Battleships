package org.ipi.battleships.core.entities;

import org.ipi.battleships.core.enums.ShipModel;
import org.ipi.battleships.core.enums.ShotResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            if (ships.stream().map(Ship::getModel).collect(Collectors.toSet()).size() != 5) {
                throw new IllegalArgumentException();
            }
        }
    }

    public int shipsRemainingCount() {
        return (int) ships.stream().filter(s -> !s.sank()).count();
    }

    public ShotResult shootAtCoordinate(Coordinate c) {
        for (Ship s : ships) {
            ShotResult result = s.hit(c);
            if (result != ShotResult.MISSED) {
                return result;
            }
        }
        return ShotResult.MISSED;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash *= 13 + ships.hashCode();
        return Integer.hashCode(hash);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == Fleet.class && obj.hashCode() == this.hashCode();
    }
}
