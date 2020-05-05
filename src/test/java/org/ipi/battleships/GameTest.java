package org.ipi.battleships;

import org.ipi.battleships.core.Game;
import org.ipi.battleships.core.player.DummyAI;
import org.ipi.battleships.core.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {


    @Test
    void gameTests() {

        Player dummyPlayer = new DummyAI();
        Game game = new Game(dummyPlayer);
        assertEquals(0, game.getShotCount());
        game.play();
        // Last position shot by the dummy AI shoud be (1, 6)
        assertEquals(51, game.getShotCount());
    }
}
