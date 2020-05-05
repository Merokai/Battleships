package org.ipi.battleships.core.player;

import org.ipi.battleships.core.entities.Coordinate;
import org.ipi.battleships.core.entities.Fleet;
import org.ipi.battleships.core.enums.ShotResult;

public interface Player {
    Coordinate requestShootCoordinate();

    Coordinate requestShootCoordinate(ShotResult lastShotResult);

    Fleet requestFleet();
}
