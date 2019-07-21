package io.joshatron.bgt.engine.turn;

import java.io.Serializable;

public class Action implements Serializable {
    public static Action createFromString(String action) {
        return new Action();
    }
}
