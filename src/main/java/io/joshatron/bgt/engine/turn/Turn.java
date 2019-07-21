package io.joshatron.bgt.engine.turn;

import io.joshatron.bgt.engine.player.PlayerIndicator;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Turn implements Serializable {
    private PlayerIndicator player;
    private List<ActionPair> actions;

    public Turn(PlayerIndicator player) {
        this.player = player;
    }

    public Turn createFromString(String turn) {
        return new Turn(PlayerIndicator.NONE);
    }
}
