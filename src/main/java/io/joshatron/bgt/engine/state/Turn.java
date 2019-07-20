package io.joshatron.bgt.engine.state;

import io.joshatron.bgt.engine.player.PlayerIndicator;
import lombok.Data;

import java.io.Serializable;

@Data
public class Turn implements Serializable {
    private PlayerIndicator player;

    public Turn(PlayerIndicator player) {
        this.player = player;
    }

    public Turn createFromString(String turn) {
        return new Turn(PlayerIndicator.NONE);
    }
}
