package io.joshatron.bgt.engine.component;

import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.player.PlayerIndicator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

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
        PieceStack<Piece> pieceStack = null;
        try {
            pieceStack = new PieceStack<>();
            pieceStack.addPiece(null);
            Assert.fail();
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.INVALID_PIECE);
            Assert.assertEquals(pieceStack.getHeight(), 0);
        }
    }

    @Test
    public void addPiecesNormal() {
        try {
            PieceStack<Piece> pieceStack = new PieceStack<>();
            List<Piece> pieces = new ArrayList<>();
            pieces.add(new Piece(PlayerIndicator.WHITE));
            pieces.add(new Piece(PlayerIndicator.BLACK));
            pieceStack.addPieces(pieces);
            Assert.assertEquals(pieceStack.getHeight(), 2);
            Assert.assertEquals(pieceStack.getPieces().get(0), new Piece(PlayerIndicator.WHITE));
            Assert.assertEquals(pieceStack.getPieces().get(1), new Piece(PlayerIndicator.BLACK));
        } catch(BoardGameEngineException e) {
            Assert.fail();
        }
    }

    @Test
    public void addPiecesNull() {
        PieceStack<Piece> pieceStack = null;
        try {
            pieceStack = new PieceStack<>();
            List<Piece> pieces = new ArrayList<>();
            pieces.add(new Piece(PlayerIndicator.WHITE));
            pieces.add(null);
            pieces.add(new Piece(PlayerIndicator.BLACK));
            pieceStack.addPieces(pieces);
            Assert.fail();
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.INVALID_PIECE);
            Assert.assertEquals(pieceStack.getHeight(), 0);
        }
    }
}
