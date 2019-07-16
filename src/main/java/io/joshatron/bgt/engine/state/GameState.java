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
    private int skip;
    private boolean reverse;

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
        this.skip = 0;
        this.reverse = false;
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

    public PlayerInfo getCurrentPlayerInfo() throws BoardGameEngineException {
        if(turnStyle != TurnStyle.IN_ORDER) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.IN_ORDER_QUESTION_ONLY);
        }

        return players.get(currentPlayer);
    }

    public PlayerInfo getNextPlayerInfo() throws BoardGameEngineException {
        if(turnStyle != TurnStyle.IN_ORDER) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.IN_ORDER_QUESTION_ONLY);
        }

        if(!reverse) {
            return players.get((currentPlayer + (1 + skip)) % players.size());
        }
        else {
            int num = currentPlayer - (1 + skip);
            while(num < 0) {
                num += players.size();
            }

            return players.get(num);
        }

    }

    public Turn getLatestTurn() {
        return gameLog.get(gameLog.size() - 1).getTurn();
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
