package io.joshatron.bgt.engine.board.grid;

import io.joshatron.bgt.engine.board.BoardLocation;
import io.joshatron.bgt.engine.board.BoardTile;
import io.joshatron.bgt.engine.board.GameBoard;
import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import lombok.Data;

@Data
public class GridBoard implements GameBoard {
    private BoardTile[][] board;
    private int width; //x size
    private int height; //y size

    public GridBoard(int width, int height, BoardTile template) {
        this.width = width;
        this.height = height;
        board = new BoardTile[width][height];

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                board[x][y] = template.makeCopy();
            }
        }
    }

    public GridBoard(BoardTile[][] initialBoard) {
        board = initialBoard;
        width = board.length;
        height = board[0].length;
    }

    @Override
    public BoardTile getTile(BoardLocation location) throws BoardGameEngineException {
        if(!onBoard(location)) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.OFF_BOARD);
        }
        GridBoardLocation loc = (GridBoardLocation)location;

        return board[loc.getX()][loc.getY()];
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
    public GameBoard makeCopy() {
        BoardTile[][] newBoard = new BoardTile[width][height];

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                newBoard[x][y] = board[x][y].makeCopy();
            }
        }

        return new GridBoard(newBoard);
    }
}
