package io.joshatron.bgt.engine.state;

import lombok.Data;

@Data
public class PlayerInfo {
    private String identifier;
    private boolean eliminated;

    public PlayerInfo(String identifier) {
        this.identifier = identifier;
        eliminated = false;
    }

    public PlayerInfo makeCopy() {
        PlayerInfo info = new PlayerInfo(identifier);
        info.setEliminated(eliminated);

        return info;
    }
}
