package io.joshatron.bgt.engine.state;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TurnLog {
    private Turn turn;
    private TurnResult turnResult;
}
