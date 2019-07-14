package io.joshatron.bgt.engine.player;

import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Pieces implements Serializable {
    private String type;
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
}
