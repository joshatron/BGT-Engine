package io.joshatron.bgt.engine.player;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlayerInfo implements Serializable {
    private PlayerIndicator identifier;
    private boolean eliminated;

    public PlayerInfo(PlayerIndicator identifier) {
        this.identifier = identifier;
        eliminated = false;
    }
}
