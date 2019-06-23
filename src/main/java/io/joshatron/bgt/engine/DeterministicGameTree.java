package io.joshatron.bgt.engine;

import io.joshatron.bgt.engine.dtos.GameState;
import io.joshatron.bgt.engine.dtos.Turn;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;

import java.util.*;

public class DeterministicGameTree {

    private StateNode root;
    private GameEngine engine;

    /*
     * Functions used in normal game play
     */
    public void executeTurn(Turn turn) throws BoardGameEngineException {
        Optional<StateNode> selected = root.getChildren().stream().filter(node -> node.getState().getLatestTurn().equals(turn)).findFirst();
        if(selected.isPresent()) {
            root = selected.get();
            root.setParent(null);
        }
        else {
            root = new StateNode(root.getState());
            engine.executeTurn(root.getState(), turn);
        }
    }

    public void undoTurn() throws BoardGameEngineException {
        StateNode newRoot = new StateNode(new GameState(root.getState()));
        engine.undoTurn(newRoot.getState());
        newRoot.addChild(root);
        root = newRoot;
    }

    public GameState getState() {
        return root.getState();
    }

    public boolean inCheck() {
        fillOutChildren(root);
        Set<StateNode> nodes = root.getChildren();
        for(StateNode node : nodes) {
            if(canWin(node)) {
                return true;
            }
        }

        return false;
    }

    public boolean canWin() {
        return canWin(root);
    }

    /*
     * Functions used by AI exploring game tree
     */
    public StateNode getRootNode() {
        return root;
    }

    public void fillOutChildren(StateNode node) {
        if(!node.isChildrenFull()) {
            List<Turn> turns = engine.getPossibleTurns(node.getState());


            turns.parallelStream().map(turn -> engine.executeTurn(new GameState(node.getState()), turn))
                    .forEach(state -> node.addChild(new StateNode(state)));
            node.setChildrenFull(true);
        }
    }

    /*
     * Helper functions
     */
    //TODO: implement
    private boolean canWin(StateNode node) {
        return false;
    }
}
