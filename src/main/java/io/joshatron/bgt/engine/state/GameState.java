package io.joshatron.bgt.engine.state;

import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
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
    private int currentPlayer;
    private TurnStyle turnStyle;
    private List<Turn> simultaneousQueue;

    public GameState(GameStatus initialStatus) {
        this(initialStatus, new ArrayList<>(), 0, TurnStyle.IN_ORDER);
    }

    public GameState(GameStatus initialStatus, List<PlayerInfo> players, int currentPlayer, TurnStyle turnStyle) {
        this.gameLog = new ArrayList<>();
        this.status = initialStatus;
        this.players = players;
        this.currentPlayer = currentPlayer;
        this.turnStyle = turnStyle;
        this.simultaneousQueue = new ArrayList<>();
    }

    public String getDisplayForPlayer(String player) {
        return "Display not implemented: " + this.toString();
    }

    public PlayerInfo getPlayerInfo(String player) throws BoardGameEngineException {
        Optional<PlayerInfo> info = players.stream().filter(playerInfo -> playerInfo.getIdentifier().equals(player)).findFirst();
        if(info.isPresent()) {
            return info.get();
        }

        throw new BoardGameEngineException(BoardGameCommonErrorCode.INVALID_PLAYER);
    }

    public Turn getLatestTurn() {
        return gameLog.get(gameLog.size() - 1).getTurn();
    }
}
