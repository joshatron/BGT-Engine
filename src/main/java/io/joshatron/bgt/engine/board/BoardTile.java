package io.joshatron.bgt.engine.board;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BoardTile<T extends BoardLocation> implements Serializable {
    private T location;
}
