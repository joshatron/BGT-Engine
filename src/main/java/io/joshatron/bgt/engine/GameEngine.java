package io.joshatron.bgt.engine;

import io.joshatron.bgt.engine.state.GameState;
import io.joshatron.bgt.engine.state.Turn;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;

import java.util.List;

public interface GameEngine {
    boolean isLegalTurn(GameState state, Turn turn);
    List<Turn> getPossibleTurns(GameState state) throws BoardGameEngineException;
    GameState executeTurn(GameState state, Turn turn) throws BoardGameEngineException;
    GameState undoTurn(GameState state) throws BoardGameEngineException;
}
