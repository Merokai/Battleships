package org.ipi.battleships;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private final List<Coordinate> coordinates;

    private final ShipModel model;

    public Ship(ShipModel model, Coordinate coordinate, Orientation orientation) {
        coordinates = new ArrayList<>();
        coordinates.add(coordinate);
        while (coordinates.size() < model.getLength()) {
            Coordinate lastPosition = coordinates.get(coordinates.size() - 1);
            Coordinate nextPosition = lastPosition.nextPointForOrientation(orientation);
            coordinates.add(nextPosition);
        }

        this.model = model;
    }

    public boolean isOnCoordinate(Coordinate c) {
        return coordinates.contains(c);
    }

    public boolean isOfModel(ShipModel model) {
        return this.model.equals(model);
    }

    @Override
    // Any ships with the same 'model' and 'coordinates' values should have the same hashCode
    public int hashCode() {
        int hash = 1;
        hash *= 13 + model.hashCode();
        hash *= 17 + coordinates.hashCode();
        return Integer.hashCode(hash);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == Ship.class && ((Ship) obj).hashCode() == hashCode();
    }

    public boolean isOverlapping(Ship ship) {
        return coordinates.stream().anyMatch(ship::isOnCoordinate);
    }
}
