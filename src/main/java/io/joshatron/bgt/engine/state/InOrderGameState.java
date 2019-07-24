package io.joshatron.bgt.engine.state;

import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.player.PlayerIndicator;
import io.joshatron.bgt.engine.player.PlayerInfo;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
public class InOrderGameState<S extends GameStatus, P extends PlayerInfo> extends GameState<S,P> {
    private int currentPlayer;
    private int skip;
    private boolean reverse;

    public InOrderGameState(S initialStatus) {
        super(initialStatus);
    }

    public InOrderGameState(S initialStatus, List<P> players, int currentPlayer) {
        super(initialStatus, players);
        this.currentPlayer = currentPlayer;
        this.skip = 0;
        this.reverse = false;
    }

    public String getDisplayForPlayer(PlayerIndicator player) {
        return "Display not implemented: " + this.toString();
    }

    public P getPlayerInfo(PlayerIndicator player) throws BoardGameEngineException {
        Optional<P> info = getPlayers().stream().filter(playerInfo -> playerInfo.getIdentifier() == player).findFirst();
        if(info.isPresent()) {
            return info.get();
        }

        throw new BoardGameEngineException(BoardGameCommonErrorCode.INVALID_PLAYER);
    }

    public P getCurrentPlayerInfo() throws BoardGameEngineException {
        if(getStatus() != null && getStatus().getStatus() == Status.COMPLETE) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.GAME_FINISHED);
        }

        return getPlayers().get(currentPlayer);
    }

    public P getNextPlayerInfo() throws BoardGameEngineException {
        if(getStatus() != null && getStatus().getStatus() == Status.COMPLETE) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.GAME_FINISHED);
        }

        if(!reverse) {
            return getPlayers().get((currentPlayer + (1 + skip)) % getPlayers().size());
        }
        else {
            int num = currentPlayer - (1 + skip);
            while(num < 0) {
                num += getPlayers().size();
            }

            return getPlayers().get(num);
        }

    }

    public void addSkip() {
        skip++;
    }

    public void resetSkip() {
        skip = 0;
    }

    public void reverse() {
        reverse = !reverse;
    }
}
