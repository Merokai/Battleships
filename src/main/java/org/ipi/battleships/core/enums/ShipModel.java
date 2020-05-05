package org.ipi.battleships.core.enums;

public enum ShipModel {
    CARRIER(5),
    BATTLESHIP(4),
    CRUISER(3),
    SUBMARINE(3),
    DESTROYER(2);

    private final int length;

    ShipModel(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
