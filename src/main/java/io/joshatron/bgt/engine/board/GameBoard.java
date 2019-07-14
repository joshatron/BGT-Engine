package io.joshatron.bgt.engine.board;

import io.joshatron.bgt.engine.exception.BoardGameEngineException;

import java.io.Serializable;

public interface GameBoard extends Serializable {
    BoardTile getTile(BoardLocation location) throws BoardGameEngineException;
    boolean onBoard(BoardLocation location);
}
