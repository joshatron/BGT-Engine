package io.joshatron.bgt.engine.state;

import lombok.Data;

import java.io.Serializable;

@Data
public class GameStatus implements Serializable {
    private Status status;

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
