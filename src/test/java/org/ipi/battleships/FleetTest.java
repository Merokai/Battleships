package org.ipi.battleships;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FleetTest {

    @Test void fleetTests(){
        Ship carrier = new Ship(ShipModel.CARRIER,new Coordinate(1, 2), Orientation.SOUTH);
        Ship battleship = new Ship(ShipModel.CARRIER,new Coordinate(1, 2), Orientation.SOUTH);
        Ship cruiser = new Ship(ShipModel.CARRIER,new Coordinate(1, 2), Orientation.SOUTH);
        Ship submarine = new Ship(ShipModel.CARRIER,new Coordinate(1, 2), Orientation.SOUTH);
        Ship destroyer = new Ship(ShipModel.CARRIER,new Coordinate(1, 2), Orientation.SOUTH);
        Fleet fleet = new Fleet(carrier, battleship, cruiser, submarine, destroyer);
    }
}
