package io.joshatron.bgt.engine.turn;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ActionPair implements Serializable {
    private Action action;
    private ActionResult actionResult;
}
