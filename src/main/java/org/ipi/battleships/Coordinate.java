package org.ipi.battleships;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate nextPointForOrientation(Orientation orientation) {
        int newX = this.x;
        int newY = this.y;

        switch (orientation) {
            case NORTH:
                newY -= 1;
                break;
            case SOUTH:
                newY += 1;
                break;
            case EAST:
                newX += 1;
                break;
            case WEST:
                newX -= 1;
                break;
        }
        return new Coordinate(newX, newY);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == Coordinate.class && ((Coordinate) obj).hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash *= 13 + x;
        hash *= 17 + y;
        return Integer.hashCode(hash);
    }
}
