package io.joshatron.bgt.engine.component.board.grid;


import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GridBoardLocationTest {
    @Test
    public void initializeWithStringSingleCharacters() {
        try {
            GridBoardLocation location = new GridBoardLocation("a1");
            Assert.assertEquals(location.getX(), 0);
            Assert.assertEquals(location.getY(), 0);
            location = new GridBoardLocation("q8");
            Assert.assertEquals(location.getX(), 16);
            Assert.assertEquals(location.getY(), 7);
        } catch(BoardGameEngineException e) {
            Assert.fail();
        }
    }

    @Test
    public void initializeWithStringMultipleCharacters() {
        try {
            GridBoardLocation location = new GridBoardLocation("ac77");
            Assert.assertEquals(location.getX(), 28);
            Assert.assertEquals(location.getY(), 76);
            location = new GridBoardLocation("bcd7796");
            Assert.assertEquals(location.getX(), 1433);
            Assert.assertEquals(location.getY(), 7795);
        } catch(BoardGameEngineException e) {
            Assert.fail();
        }
    }

    @Test
    public void initializeWithStringNegative() {
        try {
            GridBoardLocation location = new GridBoardLocation("a-7");
            Assert.fail();
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.INVALID_INPUT);
        }

        try {
            GridBoardLocation location = new GridBoardLocation("-a7");
            Assert.fail();
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.INVALID_INPUT);
        }
    }
}
