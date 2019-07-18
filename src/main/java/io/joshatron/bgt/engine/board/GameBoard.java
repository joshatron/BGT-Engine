package io.joshatron.bgt.engine.board;

import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public abstract class GameBoard implements Serializable {
    private List<BoardTile> allTiles;

    public GameBoard() {
        allTiles = new ArrayList<>();
    }

    public abstract BoardTile getTile(BoardLocation location) throws BoardGameEngineException;
    public abstract boolean onBoard(BoardLocation location);
}
