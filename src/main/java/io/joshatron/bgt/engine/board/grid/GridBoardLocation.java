package io.joshatron.bgt.engine.board.grid;

import io.joshatron.bgt.engine.board.BoardLocation;
import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GridBoardLocation implements BoardLocation {

    private int x;
    private int y;

    public GridBoardLocation(String location) throws BoardGameEngineException {
        location = location.toLowerCase();
        int breakPoint = -1;
        for(int i = 0; i < location.length(); i++) {
            if(breakPoint == -1 && Character.isDigit(location.charAt(i))) {
                breakPoint = i;
            }
            if(!Character.isDigit(location.charAt(i)) && !Character.isAlphabetic(location.charAt(i))) {
                throw new BoardGameEngineException(BoardGameCommonErrorCode.INVALID_INPUT);
            }
        }
        if(breakPoint < 1) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.OFF_BOARD);
        }

        int total = 0;
        int multiplier = 1;

        for(int i = breakPoint - 1; i >= 0; i--) {
            if(i == breakPoint - 1) {
                total += ((int) location.charAt(i) - (int) 'a') * multiplier;
            }
            else {
                total += ((int) location.charAt(i) - (int) 'a' + 1) * multiplier;
            }
            multiplier *= 26;
        }

        this.x = total;
        this.y = Integer.parseInt(location.substring(breakPoint));
    }

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
