package io.joshatron.bgt.engine.dtos;

import lombok.Data;

@Data
public class GameStatus {
    protected Status status;

    public GameStatus() {
        status = Status.INCOMPLETE;
    }

    public boolean isComplete() {
        return status == Status.COMPLETE;
    }
}
