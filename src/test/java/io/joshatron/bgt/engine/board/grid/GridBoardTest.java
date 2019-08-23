package io.joshatron.bgt.engine.board.grid;

import io.joshatron.bgt.engine.board.BoardTile;
import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GridBoardTest {
    @Test
    public void initializeBoardTemplate() {
        try {
            GridBoard<BoardTile<GridBoardLocation>> board = new GridBoard<>(3, 4, new BoardTile<>(new GridBoardLocation(0,0)));

            for(int x = 0; x < 3; x++) {
                for(int y = 0; y < 4; y++) {
                        Assert.assertEquals(board.getTile(new GridBoardLocation(x,y)), new BoardTile<>(new GridBoardLocation(x,y)));
                }
            }
        } catch(BoardGameEngineException e) {
            Assert.fail();
        }
    }

    @Test
    public void initializeBoardNegativeSize() {
        testInitialization(-3, 4);
        testInitialization(0, 4);
        testInitialization(1, 0);
        testInitialization(-5, -4);
        testInitialization(0, 0);
    }

    private void testInitialization(int width, int height) {
        try {
            GridBoard<BoardTile<GridBoardLocation>> board = new GridBoard<>(width, height, new BoardTile<>(new GridBoardLocation(0,0)));
            Assert.fail();
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.INVALID_BOARD_INITIALIZATION);
        }
    }

    @Test
    public void initializeBoardInitialBoard() {
        BoardTile<GridBoardLocation>[][] tiles = new BoardTile[2][3];
        tiles[0][0] = new BoardTile<>(new GridBoardLocation(0,0));
        tiles[1][0] = new BoardTile<>(new GridBoardLocation(1,0));
        tiles[0][1] = new BoardTile<>(new GridBoardLocation(0,1));
        tiles[1][1] = new BoardTile<>(new GridBoardLocation(1,1));
        tiles[0][2] = new BoardTile<>(new GridBoardLocation(0,2));
        tiles[1][2] = new BoardTile<>(new GridBoardLocation(1,2));

        GridBoard<BoardTile<GridBoardLocation>> board = new GridBoard<>(tiles);
        Assert.assertEquals(board.getWidth(), 2);
        Assert.assertEquals(board.getHeight(), 3);
    }

    @Test
    public void getTileOffBoard() {
        GridBoard<BoardTile<GridBoardLocation>> board = null;
        try {
            board = new GridBoard<>(3, 4, new BoardTile<>(new GridBoardLocation(0,0)));
            Assert.assertEquals(board.getTile(1,1), new BoardTile(new GridBoardLocation(1,1)));
            Assert.assertEquals(board.getTile(new GridBoardLocation(1,2)), new BoardTile(new GridBoardLocation(1,2)));
        } catch(BoardGameEngineException e) {
            Assert.fail();
        }

        testGetTile(board, -2, 2);
        testGetTile(board, 0, -1);
        testGetTile(board, 5, 2);
        testGetTile(board, 2, 4);
    }

    private void testGetTile(GridBoard<BoardTile<GridBoardLocation>> board, int x, int y) {
        try {
            board.getTile(new GridBoardLocation(x, y));
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.OFF_BOARD);
        }

        try {
            board.getTile(new GridBoardLocation(x, y));
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.OFF_BOARD);
        }
    }

    @Test
    public void onBoardOffBoard() {
        try {
            GridBoard<BoardTile<GridBoardLocation>> board = new GridBoard<>(3, 4, new BoardTile<>(new GridBoardLocation(0,0)));
            Assert.assertTrue(board.onBoard(1,2));
            Assert.assertTrue(board.onBoard(new GridBoardLocation(1,2)));
            Assert.assertFalse(board.onBoard(-3,2));
            Assert.assertFalse(board.onBoard(new GridBoardLocation(-3,2)));
            Assert.assertFalse(board.onBoard(1,-2));
            Assert.assertFalse(board.onBoard(new GridBoardLocation(1,-2)));
            Assert.assertFalse(board.onBoard(3,2));
            Assert.assertFalse(board.onBoard(new GridBoardLocation(3,2)));
            Assert.assertFalse(board.onBoard(1,4));
            Assert.assertFalse(board.onBoard(new GridBoardLocation(1,4)));
        } catch(BoardGameEngineException e) {
            Assert.fail();
        }
    }
}