package io.joshatron.bgt.engine.state;

import io.joshatron.bgt.engine.player.PlayerIndicator;
import lombok.Data;

import java.io.Serializable;

@Data
public class GameStatus implements Serializable {
    private Status status;
    private PlayerIndicator winner;

    public GameStatus() {
        status = Status.INCOMPLETE;
        winner = PlayerIndicator.NONE;
    }

    public GameStatus(Status status, PlayerIndicator winner) {
        this.status = status;
        this.winner = winner;
    }

    public boolean isComplete() {
        return status == Status.COMPLETE;
    }
}
