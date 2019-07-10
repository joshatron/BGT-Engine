package io.joshatron.bgt.engine.state;

import lombok.Data;

@Data
public class Turn {
    private String player;

    public Turn(String player) {
        this.player = player;
    }

    public Turn createFromString(String turn) {
        return new Turn(turn);
    }

    public Turn makeCopy() {
        return new Turn(player);
    }
}
