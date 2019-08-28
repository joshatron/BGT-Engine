package io.joshatron.bgt.engine.engines;

import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.state.GameState;
import io.joshatron.bgt.engine.action.Action;

import java.util.List;

public abstract class AggregateGameEngine<S extends GameState,A extends Action> implements GameEngine<S,A> {
    public abstract GameEngine<S,A> getEngineForState(S state);

    @Override
    public boolean isLegalAction(S state, A action) {
        return getEngineForState(state).isLegalAction(state, action);
    }

    @Override
    public void submitAction(S state, A action) throws BoardGameEngineException {
        getEngineForState(state).submitAction(state, action);
    }

    @Override
    public List<Action> getPossibleActions(S state) throws BoardGameEngineException {
        return getEngineForState(state).getPossibleActions(state);
    }
}
