package org.ipi.battleships;

import org.ipi.battleships.core.entities.Coordinate;
import org.ipi.battleships.core.player.DummyAI;
import org.ipi.battleships.core.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DummyAITest {

    @Test
    void dummyAITests(){

        Player dummyAI = new DummyAI();
        // First coordinate should be (1, 1)
        Assertions.assertEquals(new Coordinate(1, 1), dummyAI.requestShootCoordinate());

        // Spoiling 8 coordinates
        for(int i=0;i<8;i++){
            dummyAI.requestShootCoordinate();
        }
        // Tenth coordinate should be (10, 1)
        Assertions.assertEquals(new Coordinate(10, 1), dummyAI.requestShootCoordinate());
        // Eleventh coordinate should be (1, 2)
        Assertions.assertEquals(new Coordinate(1, 2), dummyAI.requestShootCoordinate());

        // Spoiling 88 coordinates
        for(int i=0;i<88;i++){
            dummyAI.requestShootCoordinate();
        }
        // Hundredth coordinate should be (10, 10)
        Assertions.assertEquals(new Coordinate(10, 10), dummyAI.requestShootCoordinate());




    }
}
