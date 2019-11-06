package io.joshatron.bgt.engine.engines;

import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.state.GameParameters;
import io.joshatron.bgt.engine.state.GameState;
import io.joshatron.bgt.engine.action.Action;

import java.util.List;

public abstract class AggregateGameEngine<S extends GameState,G extends GameParameters,A extends Action> implements GameEngine<S,G,A> {
    public abstract GameEngine<S,G,A> getEngineForState(S state);

    @Override
    public boolean isLegalAction(S state, A action) {
        return getEngineForState(state).isLegalAction(state, action);
    }

    @Override
    public void submitAction(S state, A action) {
        getEngineForState(state).submitAction(state, action);
    }

    @Override
    public List<A> getPossibleActions(S state) {
        return getEngineForState(state).getPossibleActions(state);
    }
}
