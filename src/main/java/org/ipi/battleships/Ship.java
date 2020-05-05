package org.ipi.battleships;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ship {

    private final List<Coordinate> coordinates;

    private final ShipModel model;
    private Set<Coordinate> hitCoordinates;

    public Ship(ShipModel model, Coordinate coordinate, Orientation orientation) {
        coordinates = new ArrayList<>();
        coordinates.add(coordinate);
        while (coordinates.size() < model.getLength()) {
            Coordinate lastPosition = coordinates.get(coordinates.size() - 1);
            Coordinate nextPosition = lastPosition.nextPointForOrientation(orientation);
            coordinates.add(nextPosition);
        }

        this.model = model;
        this.hitCoordinates = new HashSet<>();
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

    public ShotResult hit(Coordinate c) {
        // Does the coordinate belong to the ship?
        if (isOnCoordinate(c)) {
            // Has not already been hit at this coordinate?
            if (!hitCoordinates.contains(c)) {
                hitCoordinates.add(c);

                // Was the last coordinate to hit to sink the ship?
                if (hitCoordinates.size() == coordinates.size()) {
                    return ShotResult.SANK;
                }
                return ShotResult.HIT;
            }
        }
        return ShotResult.MISSED;
    }
}
