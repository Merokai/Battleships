package org.ipi.battleships;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
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
