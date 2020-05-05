package org.ipi.battleships;

public interface Player {
    Coordinate requestShootCoordinate();

    Coordinate requestShootCoordinate(ShotResult lastShotResult);
}
