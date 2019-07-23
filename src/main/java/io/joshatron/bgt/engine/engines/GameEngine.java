package io.joshatron.bgt.engine.engines;

import io.joshatron.bgt.engine.state.*;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.action.Action;

import java.util.List;

public interface GameEngine {
    boolean isLegalAction(GameState state, Action action);
    void submitAction(GameState state, Action action) throws BoardGameEngineException;
    List<Action> getPossibleActions(GameState state) throws BoardGameEngineException;
}
