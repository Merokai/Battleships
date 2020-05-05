package org.ipi.battleships;

import org.ipi.battleships.core.entities.Coordinate;
import org.ipi.battleships.core.player.DummyAI;
import org.ipi.battleships.core.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DummyAITest {

    @Test
    void dummyAITests() {

        Player dummyAI = new DummyAI();
        for (int i = 1; i <= 100; i++) {
            switch (i) {
                case 1:
                    // First coordinate should be (1, 1)
                    Assertions.assertEquals(new Coordinate(1, 1), dummyAI.requestShootCoordinate());
                    break;
                case 10:
                    // Tenth coordinate should be (10, 1)
                    Assertions.assertEquals(new Coordinate(10, 1), dummyAI.requestShootCoordinate());
                    break;
                case 11:
                    // Eleventh coordinate should be (1, 2)
                    Assertions.assertEquals(new Coordinate(1, 2), dummyAI.requestShootCoordinate());
                    break;
                case 100:
                    // Hundredth coordinate should be (10, 10)
                    Assertions.assertEquals(new Coordinate(10, 10), dummyAI.requestShootCoordinate());
                    break;
                default:
                    dummyAI.requestShootCoordinate();
                    break;
            }
        }
    }
}
