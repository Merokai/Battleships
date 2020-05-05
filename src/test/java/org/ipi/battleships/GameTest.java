package org.ipi.battleships;

import org.ipi.battleships.core.Game;
import org.ipi.battleships.core.entities.Coordinate;
import org.ipi.battleships.core.entities.Fleet;
import org.ipi.battleships.core.enums.Orientation;
import org.ipi.battleships.core.enums.ShipModel;
import org.ipi.battleships.core.player.DummyAI;
import org.ipi.battleships.core.player.Player;
import org.ipi.battleships.core.entities.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {


    @Test
    void gameTests() {
        Ship carrier = new Ship(ShipModel.CARRIER, new Coordinate(1, 2), Orientation.SOUTH);
        Ship battleship = new Ship(ShipModel.BATTLESHIP, new Coordinate(2, 2), Orientation.SOUTH);
        Ship cruiser = new Ship(ShipModel.CRUISER, new Coordinate(3, 2), Orientation.SOUTH);
        Ship submarine = new Ship(ShipModel.SUBMARINE, new Coordinate(4, 2), Orientation.SOUTH);
        Ship destroyer = new Ship(ShipModel.DESTROYER, new Coordinate(5, 2), Orientation.SOUTH);

        Fleet fleet = new Fleet(carrier, battleship, cruiser, submarine, destroyer);

        Player dummyPlayer = new DummyAI();
        Game game = new Game(fleet, dummyPlayer);
        assertEquals(0, game.getShotCount());
        game.play();
        // Last position shot by the dummy AI shoud be (1, 6)
        assertEquals(51, game.getShotCount());
    }
}
