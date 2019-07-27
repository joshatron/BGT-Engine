package io.joshatron.bgt.engine.component;

import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class PiecePile<T extends Piece> implements Serializable {
    private T type;
    private int piecesLeft;

    public void removePieces(int number) throws BoardGameEngineException {
        if(piecesLeft >= number) {
            piecesLeft -= number;
        }
        else {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.NOT_ENOUGH_PIECES);
        }
    }

    public void addPieces(int number) {
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
