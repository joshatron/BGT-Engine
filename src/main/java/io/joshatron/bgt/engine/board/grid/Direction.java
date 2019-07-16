package io.joshatron.bgt.engine.board.grid;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    NORTHEAST,
    NORTHWEST,
    SOUTHEAST,
    SOUTHWEST;

    private Direction opposite;
    private boolean diagonal;

    static {
        NORTH.opposite = SOUTH;
        SOUTH.opposite = NORTH;
        EAST.opposite = WEST;
        WEST.opposite = EAST;
        NORTHEAST.opposite = SOUTHWEST;
        NORTHWEST.opposite = SOUTHEAST;
        SOUTHEAST.opposite = NORTHWEST;
        SOUTHWEST.opposite = NORTHEAST;

        NORTH.diagonal = false;
        SOUTH.diagonal = false;
        EAST.diagonal = false;
        WEST.diagonal = false;
        NORTHEAST.diagonal = true;
        NORTHWEST.diagonal = true;
        SOUTHEAST.diagonal = true;
        SOUTHWEST.diagonal = true;
    }

    public Direction opposite() {
        return opposite;
    }

    public boolean isDiagonal() {
        return diagonal;
    }
}
