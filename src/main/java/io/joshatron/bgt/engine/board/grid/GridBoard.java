package io.joshatron.bgt.engine.board.grid;

import io.joshatron.bgt.engine.board.BoardLocation;
import io.joshatron.bgt.engine.board.BoardTile;
import io.joshatron.bgt.engine.board.GameBoard;
import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import lombok.Data;
import org.apache.commons.lang.SerializationUtils;

@Data
public class GridBoard extends GameBoard {
    private BoardTile[][] board;
    private int width; //x size
    private int height; //y size

    public GridBoard(int width, int height, BoardTile template) {
        super();
        this.width = width;
        this.height = height;
        board = new BoardTile[width][height];

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                board[x][y] = (BoardTile) SerializationUtils.clone(template);
                board[x][y].setLocation(new GridBoardLocation(x, y));
                getAllTiles().add(board[x][y]);
            }
        }
    }

    public GridBoard(BoardTile[][] initialBoard) {
        board = initialBoard;
        width = board.length;
        height = board[0].length;
    }

    public BoardTile getTile(int x, int y) throws BoardGameEngineException {
        return getTile(new GridBoardLocation(x, y));
    }

    @Override
    public BoardTile getTile(BoardLocation location) throws BoardGameEngineException {
        if(!onBoard(location)) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.OFF_BOARD);
        }
        GridBoardLocation loc = (GridBoardLocation)location;

        return board[loc.getX()][loc.getY()];
    }

    public boolean onBoard(int x, int y) {
        return onBoard(new GridBoardLocation(x, y));
    }

    @Override
    public boolean onBoard(BoardLocation location) {
        if(!(location instanceof GridBoardLocation)) {
            return false;
        }
        GridBoardLocation loc = (GridBoardLocation)location;

        return loc.getX() >= 0 && loc.getY() >= 0 && loc.getX() < width && loc.getY() < height;
    }

    @Override
    public String toString() {
        String[][] tiles = new String[width][height];
        int max = 0;

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                tiles[x][y] = board[x][y].toString();
                if(tiles[x][y].length() > max) {
                    max = tiles[x][y].length();
                }
            }
        }

        int rowLength = (3 + max) * width + 1;
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(toStringRow(rowLength));

        for(int y = 0; y < height; y++) {
            toReturn.append('|');
            for(int x = 0; x < width; x++) {
                toReturn.append(' ');
                toReturn.append(tiles[x][y]);
                toReturn.append(toStringExtraSpaces(tiles[x][y], max));
                toReturn.append(" |");
            }
            toReturn.append('\n');
            toReturn.append(toStringRow(rowLength));
        }

        return toReturn.toString();
    }

    private String toStringRow(int length) {
        StringBuilder toReturn = new StringBuilder();
        for(int i = 0; i < length; i++) {
            toReturn.append('-');
        }
        toReturn.append('\n');

        return toReturn.toString();
    }

    private String toStringExtraSpaces(String tile, int max) {
        StringBuilder toReturn = new StringBuilder();
        for(int i = tile.length(); i < max; i++) {
            toReturn.append(' ');
        }

        return toReturn.toString();
    }
}
