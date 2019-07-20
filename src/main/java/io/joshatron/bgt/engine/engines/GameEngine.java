package io.joshatron.bgt.engine.engines;

import io.joshatron.bgt.engine.state.*;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;

import java.util.List;

public interface GameEngine {
    boolean isLegalTurn(GameState state, Turn turn);
    void executeTurn(GameState state, Turn turn) throws BoardGameEngineException;
    List<Turn> getPossibleTurns(GameState state) throws BoardGameEngineException;
}
