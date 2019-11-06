package io.joshatron.bgt.engine.state;

import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.player.PlayerIndicator;
import io.joshatron.bgt.engine.player.PlayerInfo;
import io.joshatron.bgt.engine.action.Action;
import io.joshatron.bgt.engine.action.ActionPair;
import io.joshatron.bgt.engine.action.ActionResult;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class GameState<S extends GameStatus, P extends PlayerInfo, G> implements Serializable {
    private S status;
    private List<ActionPair> gameLog;
    private List<P> players;

    public GameState(S initialStatus) {
        this(initialStatus, new ArrayList<>());
    }

    public GameState(S initialStatus, List<P> players) {
        this.gameLog = new ArrayList<>();
        this.status = initialStatus;
        this.players = players;
    }

    public P getPlayerInfo(PlayerIndicator player) {
        Optional<P> info = players.stream().filter(playerInfo -> playerInfo.getIdentifier() == player).findFirst();
        if(info.isPresent()) {
            return info.get();
        }

        throw new BoardGameEngineException(BoardGameCommonErrorCode.INVALID_PLAYER);
    }

    public void addToLog(Action action, ActionResult actionResult) {
        gameLog.add(new ActionPair(action, actionResult));
    }

    public ActionPair getLatestAction() {
        return gameLog.get(gameLog.size() - 1);
    }

    public G getPlayerSpecificState(PlayerIndicator player) {
        throw new BoardGameEngineException(BoardGameCommonErrorCode.NOT_IMPLEMENTED);
    }
}
