package io.joshatron.bgt.engine.component;

import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.player.PlayerIndicator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PiecePileTest {
    @Test
    public void initializeNormal() {
        try {
            PiecePile<Piece> pile = new PiecePile<>(new Piece(PlayerIndicator.WHITE));
            Assert.assertEquals(pile.getType(), new Piece(PlayerIndicator.WHITE));
            Assert.assertEquals(pile.getPiecesLeft(), 0);
            pile = new PiecePile<>(new Piece(PlayerIndicator.RED), 5);
            Assert.assertEquals(pile.getType(), new Piece(PlayerIndicator.RED));
            Assert.assertEquals(pile.getPiecesLeft(), 5);
        } catch(BoardGameEngineException e) {
            Assert.fail();
        }
    }

    @Test
    public void initializeNegative() {
        try {
            PiecePile<Piece> pile = new PiecePile<>(new Piece(PlayerIndicator.WHITE), -5);
            Assert.fail();
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.INVALID_NUMBER);
        }
    }

    @Test
    public void initializeNull() {
        try {
            PiecePile<Piece> pile = new PiecePile<>(null, 5);
            Assert.fail();
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.INVALID_TYPE);
        }

        try {
            PiecePile<Piece> pile = new PiecePile<>(null);
            Assert.fail();
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.INVALID_TYPE);
        }
    }


    @Test
    public void addPiecesNormal() {
        try {
            PiecePile<Piece> pile = new PiecePile<>(new Piece(PlayerIndicator.WHITE));
            pile.addPieces(3);
            Assert.assertEquals(pile.getPiecesLeft(), 3);
            pile.addPieces(5);
            Assert.assertEquals(pile.getPiecesLeft(), 8);
            pile.addPieces(0);
            Assert.assertEquals(pile.getPiecesLeft(), 8);
        } catch(BoardGameEngineException e) {
            Assert.fail();
        }
    }

    @Test
    public void addPiecesNegative() {
        PiecePile<Piece> pile = null;
        try {
            pile = new PiecePile<>(new Piece(PlayerIndicator.WHITE), 3);
            pile.addPieces(-3);
            Assert.fail();
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.INVALID_NUMBER);
        }
        Assert.assertEquals(pile.getPiecesLeft(), 3);
    }

    @Test
    public void removePiecesNormal() {
        try {
            PiecePile<Piece> pile = new PiecePile<>(new Piece(PlayerIndicator.WHITE), 10);
            pile.removePieces(5);
            Assert.assertEquals(pile.getPiecesLeft(), 5);
            pile.removePieces(2);
            Assert.assertEquals(pile.getPiecesLeft(), 3);
            pile.removePieces(0);
            Assert.assertEquals(pile.getPiecesLeft(), 3);
            pile.removePieces(3);
            Assert.assertEquals(pile.getPiecesLeft(), 0);
        } catch(BoardGameEngineException e) {
            Assert.fail();
        }
    }

    @Test
    public void removePiecesNegative() {
        PiecePile<Piece> pile = null;
        try {
            pile = new PiecePile<>(new Piece(PlayerIndicator.WHITE), 10);
            pile.removePieces(-1);
            Assert.fail();
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.INVALID_NUMBER);
        }
        Assert.assertEquals(pile.getPiecesLeft(), 10);
    }

    @Test
    public void removePiecesNotEnoughLeft() {
        PiecePile<Piece> pile = null;
        try {
            pile = new PiecePile<>(new Piece(PlayerIndicator.WHITE), 10);
            pile.removePieces(11);
            Assert.fail();
        } catch(BoardGameEngineException e) {
            Assert.assertEquals(e.getCode(), BoardGameCommonErrorCode.NOT_ENOUGH_PIECES);
        }
        Assert.assertEquals(pile.getPiecesLeft(), 10);
    }

    @Test
    public void outOfPieces() {
        try {
            PiecePile<Piece> pile = new PiecePile<>(new Piece(PlayerIndicator.WHITE), 5);
            Assert.assertFalse(pile.outOfPieces());
            pile.removePieces(5);
            Assert.assertTrue(pile.outOfPieces());
        } catch(BoardGameEngineException e) {
            Assert.fail();
        }
    }
}
