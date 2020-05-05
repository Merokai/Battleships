package org.ipi.battleships;

public enum ShotResult {
    MISSED("missed"),
    HIT("hit a ship"),
    SANK("sank a ship");

    private final String prettyString;

    ShotResult(String prettyString) {
        this.prettyString = prettyString;
    }


    @Override
    public String toString() {
        return prettyString;
    }
}
