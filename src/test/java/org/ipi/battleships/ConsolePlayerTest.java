package org.ipi.battleships;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ConsolePlayerTest {

    @Test
    void consolePlayerTests() {
        Ship carrier = new Ship(ShipModel.CARRIER, new Coordinate(1, 2), Orientation.SOUTH);
        Ship battleship = new Ship(ShipModel.BATTLESHIP, new Coordinate(2, 2), Orientation.SOUTH);
        Ship cruiser = new Ship(ShipModel.CRUISER, new Coordinate(3, 2), Orientation.SOUTH);
        Ship submarine = new Ship(ShipModel.SUBMARINE, new Coordinate(4, 2), Orientation.SOUTH);
        Ship destroyer = new Ship(ShipModel.DESTROYER, new Coordinate(5, 2), Orientation.SOUTH);

        Fleet fleet = new Fleet(carrier, battleship, cruiser, submarine, destroyer);

        // Mocking user input
        InputStream fakeUserInput = new ByteArrayInputStream(
                (
                        "1 2\n" + // Hit
                                "1 3\n" + // Hit
                                "1 4\n" + // Hit
                                "1 5\n" + // Hit
                                "1 5\n" + // Miss (re-hit)
                                "1 6\n" + // Sink
                                "1 7\n"
                ).getBytes());

        // Instantiating console player with mocked InputStream
        Player consolePlayer = new ConsolePlayer("Antoine", fakeUserInput);


        assertEquals(ShootResult.HIT, fleet.shootAtCoordinate(consolePlayer.requestShootCoordinate()));
        assertEquals(ShootResult.HIT, fleet.shootAtCoordinate(consolePlayer.requestShootCoordinate()));
        assertEquals(ShootResult.HIT, fleet.shootAtCoordinate(consolePlayer.requestShootCoordinate()));
        assertEquals(ShootResult.HIT, fleet.shootAtCoordinate(consolePlayer.requestShootCoordinate()));
        assertEquals(ShootResult.MISSED, fleet.shootAtCoordinate(consolePlayer.requestShootCoordinate()));
        assertEquals(ShootResult.SANK, fleet.shootAtCoordinate(consolePlayer.requestShootCoordinate()));
    }

}
