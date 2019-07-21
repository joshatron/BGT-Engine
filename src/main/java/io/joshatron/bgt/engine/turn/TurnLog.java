package io.joshatron.bgt.engine.turn;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class TurnLog implements Serializable {
    private Turn turn;
    private ActionResult turnResult;
}
