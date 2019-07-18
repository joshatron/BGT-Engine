package io.joshatron.bgt.engine.board;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BoardTile implements Serializable {
    private BoardLocation location;
}
