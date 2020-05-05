package org.ipi.battleships;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.InputMismatchException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

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
}
