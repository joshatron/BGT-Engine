package io.joshatron.bgt.engine.engines;

import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.state.GameState;
import io.joshatron.bgt.engine.state.InOrderGameState;
import io.joshatron.bgt.engine.turn.Action;
import io.joshatron.bgt.engine.turn.ActionResult;

public abstract class InOrderGameEngine implements GameEngine {
    protected abstract boolean isActionValid(InOrderGameState state, Action action);
    protected abstract ActionResult updateState(InOrderGameState state, Action action);
    protected abstract boolean isTurnDone(InOrderGameState state);

    @Override
    public boolean isLegalAction(GameState state, Action action) {
        if(!(state instanceof InOrderGameState)) {
            return false;
        }

        if(!isPlayersTurn((InOrderGameState) state, action)) {
            return false;
        }

        return isActionValid((InOrderGameState) state, action);
    }

    private boolean isPlayersTurn(InOrderGameState state, Action action) {
        return state.getPlayers().get(state.getCurrentPlayer()).getIdentifier() == action.getPlayer();
    }

    @Override
    public void submitAction(GameState state, Action action) throws BoardGameEngineException {
        if(isLegalAction(state, action)) {
            ActionResult result = updateState((InOrderGameState) state, action);
            state.addToLog(action, result);
            if(isTurnDone((InOrderGameState) state)) {
                updateCurrentTurn((InOrderGameState) state);
            }
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
