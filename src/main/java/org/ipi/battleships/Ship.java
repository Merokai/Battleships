package org.ipi.battleships;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private List<Coordinate> coordinates;

    public Ship(ShipModel model, Coordinate coordinate, Orientation orientation) {
        coordinates = new ArrayList<>();
        coordinates.add(coordinate);
        while (coordinates.size() < model.getLength()) {
            Coordinate lastPosition = coordinates.get(coordinates.size() - 1);
            Coordinate nextPosition = lastPosition.nextPointForOrientation(orientation);
            coordinates.add(nextPosition);
        }
    }

    public boolean isOnCoordinate(Coordinate c) {
        return coordinates.contains(c);
    }
}
