package io.joshatron.bgt.engine;

import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.state.*;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;

import java.util.ArrayList;
import java.util.List;

public abstract class GameEngine {
    private TurnStyle turnStyle;

    protected abstract boolean isTurnValid(GameState state, Turn turn);
    protected abstract TurnResult updateState(GameState state, Turn turn);
    public abstract List<Turn> getPossibleTurns(GameState state) throws BoardGameEngineException;

    public GameEngine(TurnStyle style) {
        this.turnStyle = style;
    }

    public boolean isLegalTurn(GameState state, Turn turn) {
        if(!isPlayersTurn(state, turn)) {
            return false;
        }
        return isTurnValid(state, turn);
    }

    public void executeTurn(GameState state, Turn turn) throws BoardGameEngineException {
        if(isLegalTurn(state, turn)) {
            updateCurrentTurn(state, turn);
            TurnResult result = updateState(state, turn);
            state.getGameLog().add(new TurnLog(turn, result));
        }
        else {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.ILLEGAL_TURN);
        }
    }

    private void updateCurrentTurn(GameState state, Turn turn) {
        switch(turnStyle) {
            case IN_ORDER:
                state.setCurrentPlayer((state.getCurrentPlayer() + 1) % state.getPlayers().size());
                break;
            case ANYONE:
                return;
            case SIMULTANEOUS:
                if(state.getSimultaneousQueue().size() == state.getPlayers().size()) {
                    state.setSimultaneousQueue(new ArrayList<>());
                }
                state.getSimultaneousQueue().add(turn);
                break;
        }
    }

    private boolean isPlayersTurn(GameState state, Turn turn) {
        switch(turnStyle) {
            case IN_ORDER:
                return state.getPlayers().get(state.getCurrentPlayer()).getIdentifier().equals(turn.getPlayer());
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
