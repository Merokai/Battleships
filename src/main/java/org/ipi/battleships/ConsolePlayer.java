package org.ipi.battleships;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsolePlayer implements Player {
    private final String name;
    private final Scanner in;
    private final PrintStream out;

    public ConsolePlayer(String name) {
        // Default input is System.in
        this(name, System.in);
    }

    public ConsolePlayer(String name, InputStream in) {
        // Default output is System.out
        this(name, in, System.out);
    }

    public ConsolePlayer(String name, InputStream in, PrintStream out) {
        this.name = name;
        this.in = new Scanner(in);
        this.out = out;
    }

    @Override
    public Coordinate requestShootCoordinate() {
        out.println(String.format("%s, it is your turn. Please enter coordinates to shoot at. [ Expected format: <x> <y>]", name));
        return parseCoordinate();
    }

    private Coordinate parseCoordinate() {
        int x = in.nextInt();
        int y = in.nextInt();
        in.nextLine();
        return new Coordinate(x, y);
    }
}
