package io.joshatron.bgt.engine;

import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.state.*;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;

import java.util.ArrayList;
import java.util.List;

public abstract class GameEngine {
    private TurnStyle turnStyle;

    protected abstract boolean isTurnValid(GameState state, Turn turn);
    protected abstract void updateState(GameState state, Turn turn);
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
            switch(turnStyle) {
                case IN_ORDER:
                case ANYONE:
                case CUSTOM:
                        updateState(state, turn);
                        updateCurrentTurn(state, turn);
                    break;
                case SIMULTANEOUS:
                    updateCurrentTurn(state, turn);
                    if(state.getSimultaneousQueue().size() == state.getPlayers().size()) {
                        updateState(state, turn);
                    }
                    state.setSimultaneousQueue(new ArrayList<>());
            }
        } else {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.ILLEGAL_TURN);
        }
    }

    private void updateCurrentTurn(GameState state, Turn turn) {
        switch(turnStyle) {
            case IN_ORDER:
                if(!state.isReverse()) {
                    state.setCurrentPlayer((state.getCurrentPlayer() + (1 + state.getSkip())) % state.getPlayers().size());
                }
                else {
                    state.setCurrentPlayer((state.getCurrentPlayer() - (1 + state.getSkip())));
                    while(state.getCurrentPlayer() < 0) {
                        state.setCurrentPlayer(state.getCurrentPlayer() + state.getPlayers().size());
                    }
                }
                state.resetSkip();
                break;
            case ANYONE:
                break;
            case SIMULTANEOUS:
                state.getSimultaneousQueue().add(turn);
                break;
            case CUSTOM:
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
