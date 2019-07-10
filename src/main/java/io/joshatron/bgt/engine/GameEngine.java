package io.joshatron.bgt.engine;

import io.joshatron.bgt.engine.state.GameState;
import io.joshatron.bgt.engine.state.PlayerInfo;
import io.joshatron.bgt.engine.state.Turn;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.state.TurnStyle;

import java.util.List;

public abstract class GameEngine {
    private TurnStyle turnStyle;

    public GameEngine(TurnStyle style) {
        this.turnStyle = style;
    }

    public abstract boolean isLegalTurn(GameState state, Turn turn);
    public abstract List<Turn> getPossibleTurns(GameState state) throws BoardGameEngineException;
    public GameState executeTurn(GameState state, Turn turn) throws BoardGameEngineException {
        if(isLegalTurn(state, turn)) {

        }

        return null;
    }

    private void updateCurrentTurn(GameState state) {
    }

    private boolean isPlayersTurn(GameState state, Turn turn) {
        switch(turnStyle) {
            case IN_ORDER:
                return state.getPlayers().get(state.getCurrentPlayer()).equals(turn.getPlayer());
            case ANYONE:
                return true;
            case SIMULTANEOUS:
                for(Turn turn1 : state.getSimultaneousQueue()) {
                    if(turn.getPlayer().equals(turn1.getPlayer())) {
                        return false;
                    }
                }
                return true;
            default:
                return false;
        }
    }
}
