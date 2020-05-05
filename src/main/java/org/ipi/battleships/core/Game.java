package org.ipi.battleships.core;

import org.ipi.battleships.core.entities.Fleet;
import org.ipi.battleships.core.enums.ShotResult;
import org.ipi.battleships.core.player.Player;

public class Game {

    private final Fleet fleet;
    private final Player player;

    private int shots = 0;

    public Game(Player player) {
        this.fleet = player.requestFleet();
        this.player = player;
    }

    public void play() {
        ShotResult lastShot = fleet.shootAtCoordinate(player.requestShootCoordinate());
        shots++;
        while (fleet.shipsRemainingCount() > 0) {
            lastShot = fleet.shootAtCoordinate(player.requestShootCoordinate(lastShot));
            shots++;
        }
    }

    public int getShotCount() {
        return shots;
    }
}
