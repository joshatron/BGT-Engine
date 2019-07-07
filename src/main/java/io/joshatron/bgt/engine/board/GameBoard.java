package io.joshatron.bgt.engine.board;

import io.joshatron.bgt.engine.exception.BoardGameEngineException;

public interface GameBoard {
    BoardTile getTile(BoardLocation location) throws BoardGameEngineException;
    boolean onBoard(BoardLocation location);
    GameBoard makeCopy();
}
