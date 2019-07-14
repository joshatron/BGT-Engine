package io.joshatron.bgt.engine.player;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlayerInfo implements Serializable {
    private String identifier;
    private boolean eliminated;

    public PlayerInfo(String identifier) {
        this.identifier = identifier;
        eliminated = false;
    }
}
