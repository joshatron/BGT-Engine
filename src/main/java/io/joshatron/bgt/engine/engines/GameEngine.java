package io.joshatron.bgt.engine.engines;

import io.joshatron.bgt.engine.state.*;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.action.Action;

import java.util.List;

public interface GameEngine<S extends GameState,G extends GameParameters,A extends Action> {
    S createInitialStateFromParameters(G gameParameters);
    boolean isLegalAction(S state, A action);
    void submitAction(S state, A action);
    List<A> getPossibleActions(S state);
}
