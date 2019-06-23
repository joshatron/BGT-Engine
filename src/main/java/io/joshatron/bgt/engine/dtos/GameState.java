package io.joshatron.bgt.engine.dtos;

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

    public GameState(GameState state) {

    }

    public Turn getLatestTurn() {
        return turns.get(turns.size() - 1);
    }
}
