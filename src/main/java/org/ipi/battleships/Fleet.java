package org.ipi.battleships;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Fleet {

    private final Set<Ship> ships;

    public Fleet(Ship carrier, Ship battleship, Ship cruiser, Ship submarine, Ship destroyer) {
        ships = new HashSet<>();
        ships.add(carrier);
        ships.add(battleship);
        ships.add(cruiser);
        ships.add(submarine);
        ships.add(destroyer);

        // Verify if each ShipModel exists in the ships set
        Arrays.stream(ShipModel.values()).forEach((ShipModel model) -> {
            if (ships.stream().noneMatch((Ship s) -> s.isOfModel(model))) {
                throw new IllegalArgumentException();
            }
        });
    }

    public int shipsRemainingCount() {
        return ships.size();
    }
}
