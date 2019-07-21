package io.joshatron.bgt.engine.engines;

import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.player.PlayerIndicator;
import io.joshatron.bgt.engine.state.GameState;
import io.joshatron.bgt.engine.turn.Action;

import java.util.List;

public abstract class AggregateGameEngine implements GameEngine {
    public abstract GameEngine getEngineForState(GameState state);

    @Override
    public boolean isLegalAction(GameState state, Action action) {
        return getEngineForState(state).isLegalAction(state, action);
    }

    @Override
    public void submitAction(GameState state, Action action) throws BoardGameEngineException {
        getEngineForState(state).submitAction(state, action);
    }

    @Override
    public List<Action> getPossibleActions(GameState state, PlayerIndicator player) throws BoardGameEngineException {
        return getEngineForState(state).getPossibleActions(state, player);
    }
}
