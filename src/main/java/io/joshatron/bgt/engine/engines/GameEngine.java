package io.joshatron.bgt.engine.engines;

import io.joshatron.bgt.engine.state.*;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.action.Action;

import java.util.List;

public interface GameEngine<S extends GameState,A extends Action> {
    boolean isLegalAction(S state, A action);
    void submitAction(S state, A action) throws BoardGameEngineException;
    List<Action> getPossibleActions(S state) throws BoardGameEngineException;
}
