package io.joshatron.bgt.engine.action;

import io.joshatron.bgt.engine.player.PlayerIndicator;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Action implements Serializable {
    private PlayerIndicator player;

    public static Action createFromString(String action) {
        return new Action(PlayerIndicator.valueOf(action));
    }
}
