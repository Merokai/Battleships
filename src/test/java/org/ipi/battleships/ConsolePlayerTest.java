package org.ipi.battleships;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsolePlayerTest {

    @Test
    void consolePlayerInputTests() {
        Ship carrier = new Ship(ShipModel.CARRIER, new Coordinate(1, 2), Orientation.SOUTH);
        Ship battleship = new Ship(ShipModel.BATTLESHIP, new Coordinate(2, 2), Orientation.SOUTH);
        Ship cruiser = new Ship(ShipModel.CRUISER, new Coordinate(3, 2), Orientation.SOUTH);
        Ship submarine = new Ship(ShipModel.SUBMARINE, new Coordinate(4, 2), Orientation.SOUTH);
        Ship destroyer = new Ship(ShipModel.DESTROYER, new Coordinate(5, 2), Orientation.SOUTH);

        Fleet fleet = new Fleet(carrier, battleship, cruiser, submarine, destroyer);

        // Mocking user input
        InputStream fakeUserInput = new ByteArrayInputStream(
                (
                        "1 2\n" + // OK
                                "a 3\n" + // NOK
                                "1 d\n" + // NOK
                                "% 5\n" + // NOK
                                "5\n" + // NOK
                                "\n" + // NOK
                                "3 5\n" // OK
                ).getBytes());

        // Instantiating console player with mocked InputStream
        Player consolePlayer = new ConsolePlayer("Dear fake player", fakeUserInput);


        assertEquals(new Coordinate(1, 2), consolePlayer.requestShootCoordinate());
        assertThrows(NumberFormatException.class, consolePlayer::requestShootCoordinate);
        assertThrows(NumberFormatException.class, consolePlayer::requestShootCoordinate);
        assertThrows(NumberFormatException.class, consolePlayer::requestShootCoordinate);
        assertThrows(InputMismatchException.class, consolePlayer::requestShootCoordinate);
        assertThrows(NumberFormatException.class, consolePlayer::requestShootCoordinate);
        assertEquals(new Coordinate(3, 5), consolePlayer.requestShootCoordinate());
    }
}
