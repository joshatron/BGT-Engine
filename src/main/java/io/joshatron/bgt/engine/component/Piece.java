package io.joshatron.bgt.engine.component;

import io.joshatron.bgt.engine.player.PlayerIndicator;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Piece implements Component {
    PlayerIndicator owner;

    @Override
    public String toString() {
        return owner.getAcronym().toUpperCase();
    }
}
