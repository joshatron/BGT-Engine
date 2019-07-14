package io.joshatron.bgt.engine.board.grid;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    NORTHWEST,
    NORTHEAST,
    SOUTHWEST,
    SOUTHEAST;

    private Direction opposite;

    static {
        NORTH.opposite = SOUTH;
        SOUTH.opposite = NORTH;
        EAST.opposite = WEST;
        WEST.opposite = EAST;
        NORTHWEST.opposite = SOUTHEAST;
        NORTHEAST.opposite = SOUTHWEST;
        SOUTHWEST.opposite = NORTHEAST;
        SOUTHEAST.opposite = NORTHWEST;
    }

    public Direction opposite() {
        return opposite;
    }
}
