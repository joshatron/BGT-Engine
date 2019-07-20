package io.joshatron.bgt.engine.engines;

import io.joshatron.bgt.engine.state.GameState;

public interface AggregateGameEngineManager {
    GameEngine getInitialEngine();
    GameEngine updateEngine(GameState state, GameEngine currentEngine);
}
