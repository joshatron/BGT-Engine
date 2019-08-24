package io.joshatron.bgt.engine.board.grid;

import io.joshatron.bgt.engine.board.GameBoard;
import io.joshatron.bgt.engine.component.Component;
import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.SerializationUtils;

@EqualsAndHashCode(callSuper = true)
@Data
public class GridBoard<T extends Component> extends GameBoard<T,GridBoardLocation> {
    private T[][] board;
    private int width; //x size
    private int height; //y size

    public GridBoard(int width, int height, T template) throws BoardGameEngineException {
        super();

        if(width < 1 || height < 1) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.INVALID_BOARD_INITIALIZATION);
        }

        this.width = width;
        this.height = height;
        board = (T[][]) new Component[width][height];

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                board[x][y] = (T) SerializationUtils.clone(template);
                getAllTiles().add(board[x][y]);
            }
        }
    }

    public GridBoard(T[][] initialBoard) {
        board = initialBoard;
        width = board.length;
        height = board[0].length;
    }

    public T getTile(int x, int y) throws BoardGameEngineException {
        return getTile(new GridBoardLocation(x, y));
    }

    @Override
    public T getTile(GridBoardLocation location) throws BoardGameEngineException {
        if(!onBoard(location)) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.OFF_BOARD);
        }

        return board[location.getX()][location.getY()];
    }

    public boolean onBoard(int x, int y) {
        return onBoard(new GridBoardLocation(x, y));
    }

    @Override
    public boolean onBoard(GridBoardLocation location) {
        return location.getX() >= 0 && location.getY() >= 0 && location.getX() < width && location.getY() < height;
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
