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

    @Test
    public void removePiecesNormal() {
        try {
            PieceStack<Piece> pieceStack = new PieceStack<>();
            pieceStack.addPiece(new Piece(PlayerIndicator.WHITE));
            pieceStack.addPiece(new Piece(PlayerIndicator.BLACK));
            pieceStack.addPiece(new Piece(PlayerIndicator.BLUE));
            pieceStack.addPiece(new Piece(PlayerIndicator.RED));
            pieceStack.addPiece(new Piece(PlayerIndicator.PLAYER_1));
            List<Piece> pieces = pieceStack.removePieces(2);
            Assert.assertEquals(pieceStack.getHeight(), 3);
            Assert.assertEquals(pieces.size(), 2);
            Assert.assertEquals(pieces.get(0), new Piece(PlayerIndicator.RED));
            Assert.assertEquals(pieces.get(1), new Piece(PlayerIndicator.PLAYER_1));
            pieces = pieceStack.removePieces(0);
            Assert.assertEquals(pieceStack.getHeight(), 3);
            Assert.assertEquals(pieces.size(), 0);
            pieces = pieceStack.removePieces(1);
            Assert.assertEquals(pieceStack.getHeight(), 2);
            Assert.assertEquals(pieces.size(), 1);
            Assert.assertEquals(pieces.get(0), new Piece(PlayerIndicator.BLUE));
            Assert.assertEquals(pieceStack.getPieces().get(0), new Piece(PlayerIndicator.WHITE));
            Assert.assertEquals(pieceStack.getPieces().get(1), new Piece(PlayerIndicator.BLACK));
        } catch(BoardGameEngineException e) {
            Assert.fail();
        }
    }

    @Test
    public void removePiecesNegative() {
        PieceStack<Piece> pieceStack = null;
        List<Piece> pieces = null;
        try {
            pieceStack = new PieceStack<>();
            pieceStack.addPiece(new Piece(PlayerIndicator.WHITE));
            pieceStack.addPiece(new Piece(PlayerIndicator.BLACK));
            pieces = pieceStack.removePieces(-1);
            Assert.fail();
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.INVALID_NUMBER);
            Assert.assertEquals(pieceStack.getHeight(), 2);
            Assert.assertEquals(pieceStack.getPieces().get(0), new Piece(PlayerIndicator.WHITE));
            Assert.assertEquals(pieceStack.getPieces().get(1), new Piece(PlayerIndicator.BLACK));
            Assert.assertNull(pieces);
        }
    }

    @Test
    public void removePiecesTooMany() {
        PieceStack<Piece> pieceStack = null;
        List<Piece> pieces = null;
        try {
            pieceStack = new PieceStack<>();
            pieceStack.addPiece(new Piece(PlayerIndicator.WHITE));
            pieceStack.addPiece(new Piece(PlayerIndicator.BLACK));
            pieces = pieceStack.removePieces(3);
            Assert.fail();
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.TOO_MANY_PIECES_TO_REMOVE);
            Assert.assertEquals(pieceStack.getHeight(), 2);
            Assert.assertEquals(pieceStack.getPieces().get(0), new Piece(PlayerIndicator.WHITE));
            Assert.assertEquals(pieceStack.getPieces().get(1), new Piece(PlayerIndicator.BLACK));
            Assert.assertNull(pieces);
        }
    }

    @Test
    public void getTopPieceNormal() {
        try {
            PieceStack<Piece> pieceStack = new PieceStack<>();
            pieceStack.addPiece(new Piece(PlayerIndicator.WHITE));
            pieceStack.addPiece(new Piece(PlayerIndicator.BLACK));
            pieceStack.addPiece(new Piece(PlayerIndicator.BLUE));
            pieceStack.addPiece(new Piece(PlayerIndicator.RED));
            pieceStack.addPiece(new Piece(PlayerIndicator.PLAYER_1));
            Assert.assertEquals(pieceStack.getTopPiece(), new Piece(PlayerIndicator.PLAYER_1));
            pieceStack.removePieces(1);
            Assert.assertEquals(pieceStack.getTopPiece(), new Piece(PlayerIndicator.RED));
        } catch(BoardGameEngineException e) {
            Assert.fail();
        }
    }

    @Test
    public void getTopPieceEmpty() {
        try {
            PieceStack<Piece> pieceStack = new PieceStack<>();
            Assert.assertNull(pieceStack.getTopPiece());
            pieceStack.addPiece(new Piece(PlayerIndicator.WHITE));
            Assert.assertEquals(pieceStack.getTopPiece(), new Piece(PlayerIndicator.WHITE));
            pieceStack.removePieces(1);
            Assert.assertNull(pieceStack.getTopPiece());
        } catch(BoardGameEngineException e) {
            Assert.fail();
        }
    }

    @Test
    public void getTopPiecesNormal() {
        try {
            PieceStack<Piece> pieceStack = new PieceStack<>();
            pieceStack.addPiece(new Piece(PlayerIndicator.WHITE));
            pieceStack.addPiece(new Piece(PlayerIndicator.BLACK));
            pieceStack.addPiece(new Piece(PlayerIndicator.BLUE));
            pieceStack.addPiece(new Piece(PlayerIndicator.RED));
            pieceStack.addPiece(new Piece(PlayerIndicator.PLAYER_1));
            List<Piece> pieces = pieceStack.getTopPieces(2);
            Assert.assertEquals(pieceStack.getHeight(), 5);
            Assert.assertEquals(pieces.size(), 2);
            Assert.assertEquals(pieces.get(0), new Piece(PlayerIndicator.RED));
            Assert.assertEquals(pieces.get(1), new Piece(PlayerIndicator.PLAYER_1));
            pieces = pieceStack.getTopPieces(0);
            Assert.assertEquals(pieceStack.getHeight(), 5);
            Assert.assertEquals(pieces.size(), 0);
            pieces = pieceStack.getTopPieces(1);
            Assert.assertEquals(pieceStack.getHeight(), 5);
            Assert.assertEquals(pieces.size(), 1);
            Assert.assertEquals(pieces.get(0), new Piece(PlayerIndicator.PLAYER_1));
            Assert.assertEquals(pieceStack.getPieces().get(0), new Piece(PlayerIndicator.WHITE));
            Assert.assertEquals(pieceStack.getPieces().get(1), new Piece(PlayerIndicator.BLACK));
            Assert.assertEquals(pieceStack.getPieces().get(2), new Piece(PlayerIndicator.BLUE));
            Assert.assertEquals(pieceStack.getPieces().get(3), new Piece(PlayerIndicator.RED));
            Assert.assertEquals(pieceStack.getPieces().get(4), new Piece(PlayerIndicator.PLAYER_1));
        } catch(BoardGameEngineException e) {
            Assert.fail();
        }
    }

    @Test
    public void getTopPiecesNegative() {
        PieceStack<Piece> pieceStack = null;
        List<Piece> pieces = null;
        try {
            pieceStack = new PieceStack<>();
            pieceStack.addPiece(new Piece(PlayerIndicator.WHITE));
            pieceStack.addPiece(new Piece(PlayerIndicator.BLACK));
            pieces = pieceStack.getTopPieces(-1);
            Assert.fail();
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.INVALID_NUMBER);
            Assert.assertEquals(pieceStack.getHeight(), 2);
            Assert.assertEquals(pieceStack.getPieces().get(0), new Piece(PlayerIndicator.WHITE));
            Assert.assertEquals(pieceStack.getPieces().get(1), new Piece(PlayerIndicator.BLACK));
            Assert.assertNull(pieces);
        }
    }

    @Test
    public void getTopPiecesTooMany() {
        PieceStack<Piece> pieceStack = null;
        List<Piece> pieces = null;
        try {
            pieceStack = new PieceStack<>();
            pieceStack.addPiece(new Piece(PlayerIndicator.WHITE));
            pieceStack.addPiece(new Piece(PlayerIndicator.BLACK));
            pieces = pieceStack.getTopPieces(3);
            Assert.fail();
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.TOO_MANY_PIECES_TO_REMOVE);
            Assert.assertEquals(pieceStack.getHeight(), 2);
            Assert.assertEquals(pieceStack.getPieces().get(0), new Piece(PlayerIndicator.WHITE));
            Assert.assertEquals(pieceStack.getPieces().get(1), new Piece(PlayerIndicator.BLACK));
            Assert.assertNull(pieces);
        }
    }

    @Test
    public void getHeight() {
        try {
            PieceStack<Piece> pieceStack = new PieceStack<>();
            Assert.assertEquals(pieceStack.getHeight(), 0);
            pieceStack.addPiece(new Piece(PlayerIndicator.WHITE));
            Assert.assertEquals(pieceStack.getHeight(), 1);
            pieceStack.addPiece(new Piece(PlayerIndicator.BLACK));
            Assert.assertEquals(pieceStack.getHeight(), 2);
            pieceStack.removePieces(1);
            Assert.assertEquals(pieceStack.getHeight(), 1);
        } catch(BoardGameEngineException e) {
            Assert.fail();
        }
    }

    @Test
    public void isEmpty() {
        try {
            PieceStack<Piece> pieceStack = new PieceStack<>();
            Assert.assertTrue(pieceStack.isEmpty());
            pieceStack.addPiece(new Piece(PlayerIndicator.WHITE));
            Assert.assertFalse(pieceStack.isEmpty());
            pieceStack.addPiece(new Piece(PlayerIndicator.BLACK));
            Assert.assertFalse(pieceStack.isEmpty());
            pieceStack.removePieces(2);
            Assert.assertTrue(pieceStack.isEmpty());
        } catch(BoardGameEngineException e) {
            Assert.fail();
        }
    }
}
