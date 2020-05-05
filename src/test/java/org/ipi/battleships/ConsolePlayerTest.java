package org.ipi.battleships;

import org.ipi.battleships.core.entities.Coordinate;
import org.ipi.battleships.core.entities.Fleet;
import org.ipi.battleships.core.entities.Ship;
import org.ipi.battleships.core.enums.Orientation;
import org.ipi.battleships.core.enums.ShipModel;
import org.ipi.battleships.core.enums.ShotResult;
import org.ipi.battleships.core.player.ConsolePlayer;
import org.ipi.battleships.core.player.Player;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.InputMismatchException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsolePlayerTest {

    @Test
    void consolePlayerInputTests() {
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

    @Test
    void consolePlayerOutputTests() {
        // Mocking user input
        InputStream fakeUserInput = new ByteArrayInputStream(
                (
                        "1 2\n" +
                                "1 3\n" +
                                "3 5\n"
                ).getBytes());

        // Mocking console output
        OutputStream fakeConsoleOutput = new ByteArrayOutputStream();

        // Instantiating console player with mocked InputStream
        Player consolePlayer = new ConsolePlayer("Dear fake player", fakeUserInput, fakeConsoleOutput);
        consolePlayer.requestShootCoordinate(ShotResult.HIT);
        consolePlayer.requestShootCoordinate(ShotResult.MISSED);
        consolePlayer.requestShootCoordinate(ShotResult.SANK);

        assertEquals(("Dear fake player, it's your turn. Your previous shot hit a ship. Please enter coordinates to shoot at. [ Expected format: <x> <y>]" +
                "Dear fake player, it's your turn. Your previous shot missed. Please enter coordinates to shoot at. [ Expected format: <x> <y>]" +
                "Dear fake player, it's your turn. Your previous shot sank a ship. Please enter coordinates to shoot at. [ Expected format: <x> <y>]").lines().collect(Collectors.joining()), fakeConsoleOutput.toString().lines().collect(Collectors.joining()));
    }

    @Test
    void consolePlayerCreateFleetTests() {
        // Mocking user input
        InputStream fakeUserInput = new ByteArrayInputStream(
                (
                        "1 2 SOUTH\n" +
                                "2 2 SOUTH\n" +
                                "3 2 SOUTH\n" +
                                "4 2 SOUTH\n" +
                                "5 2 SOUTH\n"
                ).getBytes());


        Ship carrier = new Ship(ShipModel.CARRIER, new Coordinate(1, 2), Orientation.SOUTH);
        Ship battleship = new Ship(ShipModel.BATTLESHIP, new Coordinate(2, 2), Orientation.SOUTH);
        Ship cruiser = new Ship(ShipModel.CRUISER, new Coordinate(3, 2), Orientation.SOUTH);
        Ship submarine = new Ship(ShipModel.SUBMARINE, new Coordinate(4, 2), Orientation.SOUTH);
        Ship destroyer = new Ship(ShipModel.DESTROYER, new Coordinate(5, 2), Orientation.SOUTH);

        Fleet fleet = new Fleet(carrier, battleship, cruiser, submarine, destroyer);

        // Instantiating console player with mocked InputStream
        Player consolePlayer = new ConsolePlayer("Dear fake player", fakeUserInput);

        assertEquals(fleet, consolePlayer.requestFleet());
    }
}
