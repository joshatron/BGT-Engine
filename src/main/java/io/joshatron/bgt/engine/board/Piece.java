package io.joshatron.bgt.engine.board;

import io.joshatron.bgt.engine.player.PlayerIndicator;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Piece implements Serializable {
    PlayerIndicator owner;
}
