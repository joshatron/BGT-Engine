package io.joshatron.bgt.engine.inorder;

import io.joshatron.bgt.engine.action.Action;
import io.joshatron.bgt.engine.board.grid.GridBoardLocation;
import io.joshatron.bgt.engine.player.PlayerIndicator;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TicTacToeAction extends Action {
    private GridBoardLocation location;

    public TicTacToeAction(PlayerIndicator player, GridBoardLocation location) {
        super(player);
        this.location = location;
    }
}
