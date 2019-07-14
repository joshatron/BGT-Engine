package io.joshatron.bgt.engine.board.grid;

import io.joshatron.bgt.engine.board.BoardLocation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GridBoardLocation implements BoardLocation {

    private int x;
    private int y;

    public void move(Direction direction, int distance) {
        switch(direction) {
            case NORTH:
                y -= distance;
                break;
            case SOUTH:
                y += distance;
                break;
            case EAST:
                x += distance;
                break;
            case WEST:
                x -= distance;
                break;
            case NORTHWEST:
                y -= distance;
                x -= distance;
                break;
            case NORTHEAST:
                y -= distance;
                x += distance;
                break;
            case SOUTHWEST:
                y += distance;
                x -= distance;
                break;
            case SOUTHEAST:
                y += distance;
                x += distance;
                break;
        }
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
