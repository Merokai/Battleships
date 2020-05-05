package org.ipi.battleships;

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
}
