package org.ipi.battleships;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    public ConsolePlayer(String name, InputStream in, OutputStream out) {
        this.name = name;
        this.in = new Scanner(in);
        this.out = new PrintStream(out);
    }

    @Override
    public Coordinate requestShootCoordinate() {
        out.println(String.format("%s, it is your turn. Please enter coordinates to shoot at. [ Expected format: <x> <y>]", name));
        return parseCoordinate();
    }

    private Coordinate parseCoordinate() {
        List<Integer> values = Arrays.stream(in.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        if (values.size() != 2) {
            throw new InputMismatchException();
        }
        return new Coordinate(values.get(0), values.get(1));
    }
}
