package org.ipi.battleships.core.entities;


import org.ipi.battleships.core.enums.Orientation;
import org.ipi.battleships.core.enums.ShipModel;
import org.ipi.battleships.core.enums.ShipPosition;
import org.ipi.battleships.core.enums.ShotResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ship {

    private final Map<Coordinate, ShipPosition> shipPositions;

    private final ShipModel model;

    public Ship(ShipModel model, Coordinate coordinate, Orientation orientation) {
        shipPositions = initializePositions(model, coordinate, orientation);
        this.model = model;
    }

    public ShipModel getModel() {
        return model;
    }

    public boolean isOnCoordinate(Coordinate c) {
        return shipPositions.containsKey(c);
    }

    @Override
    // Any ships with the same 'model' and 'coordinates' values should have the same hashCode
    public int hashCode() {
        int hash = 1;
        hash *= 13 + model.hashCode();
        hash *= 17 + shipPositions.hashCode();
        return Integer.hashCode(hash);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == Ship.class && obj.hashCode() == hashCode();
    }

    public boolean isOverlapping(Ship ship) {
        return shipPositions.keySet().stream().anyMatch(ship::isOnCoordinate);
    }

    public ShotResult hit(Coordinate c) {
        // Does the coordinate belong to the ship?
        if (!isOnCoordinate(c)) {
            return ShotResult.MISSED;
        }

        // Has already been hit?
        if (shipPositions.get(c).equals(ShipPosition.HIT)) {
            return ShotResult.MISSED;
        }

        shipPositions.put(c, ShipPosition.HIT);
        return sank() ? ShotResult.SANK : ShotResult.HIT;
    }

    public boolean sank() {
        // If no position is undamaged, then the ship sank
        return shipPositions.values().stream().noneMatch(p -> p.equals(ShipPosition.UNDAMAGED));
    }

    private Map<Coordinate, ShipPosition> initializePositions(ShipModel model, Coordinate coordinate, Orientation orientation) {
        List<Coordinate> coordinates = new ArrayList<>();
        coordinates.add(coordinate);
        while (coordinates.size() < model.getLength()) {
            Coordinate lastPosition = coordinates.get(coordinates.size() - 1);
            Coordinate nextPosition = lastPosition.nextPointForOrientation(orientation);
            coordinates.add(nextPosition);
        }
        return coordinates.stream().collect(Collectors.toMap(c -> c, b -> ShipPosition.UNDAMAGED));
    }
}
