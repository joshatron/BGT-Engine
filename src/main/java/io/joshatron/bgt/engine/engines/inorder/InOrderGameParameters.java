package io.joshatron.bgt.engine.engines.inorder;

import io.joshatron.bgt.engine.player.PlayerIndicator;
import io.joshatron.bgt.engine.state.GameParameters;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InOrderGameParameters implements GameParameters {
    private PlayerIndicator firstPlayer;
}
