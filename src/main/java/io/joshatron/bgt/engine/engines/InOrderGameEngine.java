package io.joshatron.bgt.engine.engines;

import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.state.GameState;
import io.joshatron.bgt.engine.state.InOrderGameState;
import io.joshatron.bgt.engine.state.Turn;

public abstract class InOrderGameEngine implements GameEngine {
    protected abstract boolean isTurnValid(GameState state, Turn turn);
    protected abstract void updateState(GameState state, Turn turn);

    @Override
    public boolean isLegalTurn(GameState state, Turn turn) {
        if(!(state instanceof InOrderGameState)) {
            return false;
        }

        if(!isPlayersTurn((InOrderGameState) state, turn)) {
            return false;
        }

        return isTurnValid(state, turn);
    }

    private boolean isPlayersTurn(InOrderGameState state, Turn turn) {
        return state.getPlayers().get(state.getCurrentPlayer()).getIdentifier().equals(turn.getPlayer());
    }

    @Override
    public void executeTurn(GameState state, Turn turn) throws BoardGameEngineException {
        if(isLegalTurn(state, turn)) {
            updateState(state, turn);
            updateCurrentTurn((InOrderGameState) state);
        } else {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.ILLEGAL_TURN);
        }
    }

    private void updateCurrentTurn(InOrderGameState state) {
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
    }
}
