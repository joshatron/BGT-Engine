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

        GameState state = new GameState(status.makeCopy(), playerCopy, currentPlayer, turnStyle);
        state.setGameLog(logCopy);

        if(turnStyle == TurnStyle.SIMULTANEOUS) {
            List<Turn> simultaneousCopy = new ArrayList<>();
            for(Turn turn : simultaneousQueue) {
                simultaneousCopy.add(turn.makeCopy());
            }
            state.setSimultaneousQueue(simultaneousCopy);
        }

        return state;
    }
}
