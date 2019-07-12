package io.joshatron.bgt.engine;

import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.state.GameState;
import io.joshatron.bgt.engine.state.Turn;
import io.joshatron.bgt.engine.state.TurnStyle;

import java.util.List;

public class AggregateGameEngine extends GameEngine {
    private AggregateGameEngineManager manager;
    private GameEngine currentEngine;

    public AggregateGameEngine(TurnStyle style, AggregateGameEngineManager manager) {
        super(style);
        this.manager = manager;
        this.currentEngine = manager.getInitialEngine();
    }

    @Override
    protected boolean isTurnValid(GameState state, Turn turn) {
        return currentEngine.isTurnValid(state, turn);
    }

    @Override
    protected void updateState(GameState state, Turn turn) {
        currentEngine.updateState(state, turn);
        currentEngine = manager.updateEngine(state, currentEngine);
    }

    @Override
    public List<Turn> getPossibleTurns(GameState state) throws BoardGameEngineException {
        return currentEngine.getPossibleTurns(state);
    }
}
