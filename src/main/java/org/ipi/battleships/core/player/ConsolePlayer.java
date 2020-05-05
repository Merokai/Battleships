package org.ipi.battleships.core.player;

import org.ipi.battleships.core.entities.Coordinate;
import org.ipi.battleships.core.entities.Fleet;
import org.ipi.battleships.core.entities.Ship;
import org.ipi.battleships.core.enums.Orientation;
import org.ipi.battleships.core.enums.ShipModel;
import org.ipi.battleships.core.enums.ShotResult;

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
        out.println(String.format("%s, it's your turn. Please enter coordinates to shoot at. [ Expected format: <x> <y>]", name));
        return parseCoordinate();
    }

    @Override
    public Coordinate requestShootCoordinate(ShotResult lastShotResult) {
        out.println(String.format("%s, it's your turn. Your previous shot %s. Please enter coordinates to shoot at. [ Expected format: <x> <y>]", name, lastShotResult.toString()));
        return parseCoordinate();
    }

    @Override
    public Fleet requestFleet() {
        Ship carrier, battleship, cruiser, submarine, destroyer;
        out.println(String.format("%s, Please provide your carrier position and orientation. [ Expected format: <x> <y> ORIENTATION", name));
        carrier = parseShip(ShipModel.CARRIER);
        out.println(String.format("%s, Please provide your battleship position and orientation. [ Expected format: <x> <y> ORIENTATION", name));
        battleship = parseShip(ShipModel.BATTLESHIP);
        out.println(String.format("%s, Please provide your cruiser position and orientation. [ Expected format: <x> <y> ORIENTATION", name));
        cruiser = parseShip(ShipModel.CRUISER);
        out.println(String.format("%s, Please provide your submarine position and orientation. [ Expected format: <x> <y> ORIENTATION", name));
        submarine = parseShip(ShipModel.SUBMARINE);
        out.println(String.format("%s, Please provide your destroyer position and orientation. [ Expected format: <x> <y> ORIENTATION", name));
        destroyer = parseShip(ShipModel.DESTROYER);

        return new Fleet(carrier, battleship, cruiser, submarine, destroyer);
    }

    private Coordinate parseCoordinate() {
        List<Integer> values = Arrays.stream(in.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        if (values.size() != 2) {
            throw new InputMismatchException();
        }
        return new Coordinate(values.get(0), values.get(1));
    }

    private Ship parseShip(ShipModel model) {
        List<String> values = Arrays.stream(in.nextLine().split(" ")).collect(Collectors.toList());
        if (values.size() != 3) {
            throw new InputMismatchException();
        }
        Coordinate c = new Coordinate(Integer.parseInt(values.get(0)), Integer.parseInt(values.get(1)));
        Orientation orientation = Orientation.valueOf(values.get(2));

        return new Ship(model, c, orientation);
    }
}
