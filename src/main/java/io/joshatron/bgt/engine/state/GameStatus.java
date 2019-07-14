package io.joshatron.bgt.engine.state;

import lombok.Data;

@Data
public class GameStatus {
    protected Status status;

    public GameStatus() {
        status = Status.INCOMPLETE;
    }

    public GameStatus(Status status) {
        this.status = status;
    }

    public boolean isComplete() {
        return status == Status.COMPLETE;
    }
}
