package io.joshatron.bgt.engine.component.board.grid;

import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;

public enum Direction {
    NORTH(false, "n"),
    SOUTH(false, "s"),
    EAST(false, "e"),
    WEST(false, "w"),
    NORTHEAST(true, "ne"),
    NORTHWEST(true, "nw"),
    SOUTHEAST(true, "ne"),
    SOUTHWEST(true, "nw");

    private Direction opposite;
    private boolean diagonal;
    private String acronym;

    Direction(boolean diagonal, String acronym) {
        this.diagonal = diagonal;
        this.acronym = acronym;
    }

    static {
        NORTH.opposite = SOUTH;
        SOUTH.opposite = NORTH;
        EAST.opposite = WEST;
        WEST.opposite = EAST;
        NORTHEAST.opposite = SOUTHWEST;
        NORTHWEST.opposite = SOUTHEAST;
        SOUTHEAST.opposite = NORTHWEST;
        SOUTHWEST.opposite = NORTHEAST;
    }

    public Direction opposite() {
        return opposite;
    }

    public boolean isDiagonal() {
        return diagonal;
    }

    public String getAcronym() {
        return acronym;
    }

    public static Direction fromString(String direction) {
        for(Direction dir : Direction.values()) {
            if(dir.acronym.equalsIgnoreCase(direction)) {
                return dir;
            }
        }

        throw new BoardGameEngineException(BoardGameCommonErrorCode.INVALID_DIRECTION);
    }
}
