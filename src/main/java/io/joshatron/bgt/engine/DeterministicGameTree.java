package io.joshatron.bgt.engine;

import io.joshatron.bgt.engine.state.GameState;
import io.joshatron.bgt.engine.state.Turn;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;

import java.util.*;

public class DeterministicGameTree {

    private StateNode root;
    private GameEngine engine;

    public DeterministicGameTree(GameState initialState, GameEngine engine) {
        this.root = new StateNode(initialState);
        this.engine = engine;
    }

    public void executeTurnOnRoot(Turn turn) throws BoardGameEngineException {
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

    public void undoTurnOnRoot() throws BoardGameEngineException {
        StateNode newRoot = new StateNode(root.getState().makeCopy());
        engine.undoTurn(newRoot.getState());
        newRoot.addChild(root);
        root = newRoot;
    }

    public StateNode getRootNode() {
        return root;
    }

    private boolean canWin(StateNode node) throws BoardGameEngineException {
        fillOutChildren(node);
        for(StateNode n : node.getChildren()) {
            if(n.getState().getStatus().isComplete()) {
                return true;
            }
        }

        return false;
    }

    public void fillOutChildren(StateNode node) throws BoardGameEngineException {
        if(!node.isChildrenFull()) {
            List<Turn> turns = engine.getPossibleTurns(node.getState());


            turns.parallelStream().map(turn -> {
                try {
                    return engine.executeTurn(node.getState().makeCopy(), turn);
                } catch(BoardGameEngineException e) {
                    return null;
                }
            })
                    .forEach(state -> node.addChild(new StateNode(state)));
            node.setChildrenFull(true);
        }
    }
}
