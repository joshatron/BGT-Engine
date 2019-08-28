package io.joshatron.bgt.engine.inorder;

import io.joshatron.bgt.engine.board.grid.GridBoard;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.player.PlayerIndicator;
import io.joshatron.bgt.engine.player.PlayerInfo;
import io.joshatron.bgt.engine.state.GameStatus;
import io.joshatron.bgt.engine.state.InOrderGameState;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TicTacToeState extends InOrderGameState<GameStatus, PlayerInfo> {
    private GridBoard<TicTacToePiece> board;

    public TicTacToeState() throws BoardGameEngineException {
        super(new GameStatus());
        this.getPlayers().add(new PlayerInfo(PlayerIndicator.PLAYER_0));
        this.getPlayers().add(new PlayerInfo(PlayerIndicator.PLAYER_1));
        this.setCurrentPlayer(0);
        this.board = new GridBoard<>(3, 3, new TicTacToePiece(PlayerIndicator.NONE));
    }
}
