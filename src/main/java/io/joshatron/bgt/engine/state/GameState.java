package io.joshatron.bgt.engine.state;

import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.player.PlayerIndicator;
import io.joshatron.bgt.engine.player.PlayerInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class GameState implements Serializable {
    private GameStatus status;
    private List<TurnLog> gameLog;
    private List<PlayerInfo> players;

    public GameState(GameStatus initialStatus) {
        this(initialStatus, new ArrayList<>());
    }

    public GameState(GameStatus initialStatus, List<PlayerInfo> players) {
        this.gameLog = new ArrayList<>();
        this.status = initialStatus;
        this.players = players;
    }

    public String getDisplayForPlayer(PlayerIndicator player) {
        return "Display not implemented: " + this.toString();
    }

    public PlayerInfo getPlayerInfo(PlayerIndicator player) throws BoardGameEngineException {
        Optional<PlayerInfo> info = players.stream().filter(playerInfo -> playerInfo.getIdentifier() == player).findFirst();
        if(info.isPresent()) {
            return info.get();
        }

        throw new BoardGameEngineException(BoardGameCommonErrorCode.INVALID_PLAYER);
    }

    public Turn getLatestTurn() {
        return gameLog.get(gameLog.size() - 1).getTurn();
    }
}
