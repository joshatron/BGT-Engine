package io.joshatron.bgt.engine.state;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class TurnLog implements Serializable {
    private Turn turn;
    private TurnResult turnResult;
}
