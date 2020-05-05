package org.ipi.battleships;

public class Game {

    private final Fleet fleet;
    private final Player player;

    private int shots = 0;

    public Game(Fleet fleet, Player player) {
        this.fleet = fleet;
        this.player = player;
    }

    public void play() {
        ShotResult lastShot = fleet.shootAtCoordinate(player.requestShootCoordinate());
        shots++;
        while(fleet.shipsRemainingCount() > 0){
            lastShot = fleet.shootAtCoordinate(player.requestShootCoordinate(lastShot));
            shots++;
        }
    }

    public int getShotCount() {
        return shots;
    }
}
