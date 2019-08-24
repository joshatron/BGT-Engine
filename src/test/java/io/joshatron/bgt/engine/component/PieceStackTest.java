package io.joshatron.bgt.engine.component;

import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.player.PlayerIndicator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PieceStackTest {
    @Test
    public void addPieceNormal() {
        try {
            PieceStack<Piece> pieceStack = new PieceStack<>();
            pieceStack.addPiece(new Piece(PlayerIndicator.WHITE));
            pieceStack.addPiece(new Piece(PlayerIndicator.BLACK));
            Assert.assertEquals(pieceStack.getHeight(), 2);
            Assert.assertEquals(pieceStack.getPieces().get(0), new Piece(PlayerIndicator.WHITE));
            Assert.assertEquals(pieceStack.getPieces().get(1), new Piece(PlayerIndicator.BLACK));
        } catch(BoardGameEngineException e) {
            Assert.fail();
        }
    }

    @Test
    public void addPieceNull() {
        try {
            PieceStack<Piece> pieceStack = new PieceStack<>();
            pieceStack.addPiece(null);
            Assert.fail();
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.INVALID_PIECE);
        }
    }
}
