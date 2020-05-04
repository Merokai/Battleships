package org.ipi.battleships;

import java.util.HashSet;
import java.util.Set;

public class Fleet {

    private final Set<Ship> ships;

    public Fleet(Ship carrier, Ship battleship, Ship cruiser, Ship submarine, Ship destroyer) {
        ships = new HashSet<>();
        ships.add(carrier);
        ships.add(battleship);
        ships.add(cruiser);
        ships.add(submarine);
        ships.add(destroyer);
    }

    public int shipsRemainingCount() {
        return ships.size();
    }
}
