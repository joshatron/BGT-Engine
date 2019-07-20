package io.joshatron.bgt.engine.engines;

import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.state.GameState;
import io.joshatron.bgt.engine.state.Turn;

import java.util.List;

public class AggregateGameEngine implements GameEngine {
    private AggregateGameEngineManager manager;
    private GameEngine currentEngine;

    public AggregateGameEngine(AggregateGameEngineManager manager) {
        this.manager = manager;
        this.currentEngine = manager.getInitialEngine();
    }

    @Override
    public boolean isLegalTurn(GameState state, Turn turn) {
        return currentEngine.isLegalTurn(state, turn);
    }

    @Override
    public void executeTurn(GameState state, Turn turn) throws BoardGameEngineException {
        currentEngine.executeTurn(state, turn);
        currentEngine = manager.updateEngine(state, currentEngine);
    }

    @Override
    public List<Turn> getPossibleTurns(GameState state) throws BoardGameEngineException {
        return currentEngine.getPossibleTurns(state);
    }
}
