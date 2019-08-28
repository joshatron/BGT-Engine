package io.joshatron.bgt.engine.engines;

import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.state.InOrderGameState;
import io.joshatron.bgt.engine.action.Action;
import io.joshatron.bgt.engine.action.ActionResult;

public abstract class InOrderGameEngine<S extends InOrderGameState,A extends Action> implements GameEngine<S,A> {
    protected abstract boolean isActionValid(S state, A action);
    protected abstract ActionResult updateState(S state, A action) throws BoardGameEngineException;
    protected abstract boolean isTurnDone(S state);

    @Override
    public boolean isLegalAction(S state, A action) {
        try {
            if(!isPlayersTurn(state, action)) {
                return false;
            }
        } catch(BoardGameEngineException e) {
            return false;
        }

        return isActionValid(state, action);
    }

    private boolean isPlayersTurn(S state, A action) throws BoardGameEngineException {
        return state.getCurrentPlayerInfo().getIdentifier() == action.getPlayer();
    }

    @Override
    public void submitAction(S state, A action) throws BoardGameEngineException {
        if(isLegalAction(state, action)) {
            ActionResult result = updateState(state, action);
            state.addToLog(action, result);
            if(isTurnDone(state)) {
                updateCurrentTurn(state);
            }
        } else {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.ILLEGAL_TURN);
        }
    }

    private void updateCurrentTurn(S state) {
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
