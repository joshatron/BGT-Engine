package io.joshatron.bgt.engine.component.piece;

import io.joshatron.bgt.engine.component.Component;
import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import lombok.Data;

@Data
public class PiecePile<T extends Piece> implements Component {
    private T type;
    private int piecesLeft;

    public PiecePile(T type, int initialAmount) throws BoardGameEngineException {
        if(type == null) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.INVALID_TYPE);
        }
        if(initialAmount < 0) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.INVALID_NUMBER);
        }

        this.type = type;
        this.piecesLeft = initialAmount;
    }

    public PiecePile(T type) throws BoardGameEngineException {
        if(type == null) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.INVALID_TYPE);
        }

        this.type = type;
        this.piecesLeft = 0;
    }

    public void removePieces(int number) throws BoardGameEngineException {
        if(number < 0) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.INVALID_NUMBER);
        }
        else if(number > piecesLeft) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.NOT_ENOUGH_PIECES);
        }
        else {
            piecesLeft -= number;
        }
    }

    public void addPieces(int number) throws BoardGameEngineException {
        if(number < 0) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.INVALID_NUMBER);
        }
        piecesLeft += number;
    }

    public boolean outOfPieces() {
        return piecesLeft == 0;
    }

    @Override
    public String toString() {
        return type.toString() + ": " + piecesLeft;
    }
}
