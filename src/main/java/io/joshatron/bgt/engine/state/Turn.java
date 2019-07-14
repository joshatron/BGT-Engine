package io.joshatron.bgt.engine.state;

import lombok.Data;

import java.io.Serializable;

@Data
public class Turn implements Serializable {
    private String player;

    public Turn(String player) {
        this.player = player;
    }

    public Turn createFromString(String turn) {
        return new Turn(turn);
    }
}
