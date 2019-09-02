package io.joshatron.bgt.engine.component.board;

import io.joshatron.bgt.engine.component.Component;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class GameBoard<T extends Component,L extends BoardLocation> implements Component {
    private List<T> allTiles;
    private List<L> allLocations;

    public GameBoard() {
        allTiles = new ArrayList<>();
        allLocations = new ArrayList<>();
    }

    public abstract T getTile(L location) throws BoardGameEngineException;
    public abstract boolean onBoard(L location);
}
