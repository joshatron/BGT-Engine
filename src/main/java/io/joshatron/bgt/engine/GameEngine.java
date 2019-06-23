package io.joshatron.bgt.engine;

import io.joshatron.bgt.engine.dtos.GameState;
import io.joshatron.bgt.engine.dtos.Turn;

import java.util.List;

public interface GameEngine {
    boolean isLegalTurn(GameState state, Turn turn);
    List<Turn> getPossibleTurns(GameState state);
    GameState executeTurn(GameState state, Turn turn);
    GameState undoTurn(GameState state);
}
