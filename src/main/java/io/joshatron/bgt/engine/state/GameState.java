package io.joshatron.bgt.engine.state;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GameState {
    protected GameStatus status;
    protected List<Turn> turns;

    public GameState() {
        status = new GameStatus();
        turns = new ArrayList<>();
    }

    public Turn getLatestTurn() {
        return turns.get(turns.size() - 1);
    }

    public GameState makeCopy() {
        GameState state = new GameState();
        state.setStatus(status.makeCopy());

        List<Turn> turnsCopy = new ArrayList<>();
        for(Turn turn : turns) {
            turnsCopy.add(turn.makeCopy());
        }
        state.setTurns(turnsCopy);

        return state;
    }
}
