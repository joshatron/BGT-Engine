package io.joshatron.bgt.engine.inorder;

import io.joshatron.bgt.engine.board.grid.GridBoardLocation;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.player.PlayerIndicator;

public class TicTacToe {
    public static void main(String[] args) {
        try {
            TicTacToeState state = new TicTacToeState();
            TicTacToeEngine engine = new TicTacToeEngine();

            System.out.println(state.getBoard());
            if(state.getCurrentPlayerInfo().getIdentifier() == PlayerIndicator.PLAYER_0) {
                System.out.println("Player turn: X");
            }
            else {
                System.out.println("Player turn: O");
            }
            engine.submitAction(state, new TicTacToeAction(PlayerIndicator.PLAYER_0, new GridBoardLocation(1,1)));
            System.out.println(state.getBoard());
        } catch (BoardGameEngineException e) {
            e.printStackTrace();
        }
    }
}
