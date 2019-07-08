package io.joshatron.bgt.engine.state;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GameState {
    private GameStatus status;
    private List<TurnLog> gameLog;
    private List<PlayerInfo> players;
    private int currentPlayer;

    public GameState(GameStatus initialStatus, List<PlayerInfo> players) {
        this(initialStatus, players, 0);
    }

    public GameState(GameStatus initialStatus, List<PlayerInfo> players, int firstPlayer) {
        this.gameLog = new ArrayList<>();
        this.status = initialStatus;
        this.players = players;
        this.currentPlayer = firstPlayer;
    }

    public String getDisplayForCurrentPlayer() {
        return "Display not implemented: " + this.toString();
    }

    public Turn getLatestTurn() {
        return gameLog.get(gameLog.size() - 1).getTurn();
    }

    public GameState makeCopy() {
        List<TurnLog> logCopy = new ArrayList<>();
        for(TurnLog turn : gameLog) {
            logCopy.add(turn.makeCopy());
        }

        List<PlayerInfo> playerCopy = new ArrayList<>();
        for(PlayerInfo info : players) {
            playerCopy.add(info.makeCopy());
        }

        GameState state = new GameState(status.makeCopy(), playerCopy, currentPlayer);
        state.setGameLog(logCopy);

        return state;
    }
}
