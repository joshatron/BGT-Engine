package io.joshatron.bgt.engine.tictactoe;

import io.joshatron.bgt.engine.board.grid.GridBoardLocation;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.player.PlayerIndicator;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        try {
            TicTacToeState state = new TicTacToeState();
            TicTacToeEngine engine = new TicTacToeEngine();

            while(!state.getStatus().isComplete()) {
                System.out.println(state.getBoard());
                if(state.getCurrentPlayerInfo().getIdentifier() == PlayerIndicator.PLAYER_0) {
                    System.out.println("Player turn: X");
                } else {
                    System.out.println("Player turn: O");
                }
                System.out.print("Your move: ");
                String move = new Scanner(System.in).nextLine().trim();
                engine.submitAction(state, new TicTacToeAction(state.getCurrentPlayerInfo().getIdentifier(), new GridBoardLocation(move)));
            }
            System.out.println(state.getBoard());
        } catch (BoardGameEngineException e) {
            e.printStackTrace();
        }
    }
}
