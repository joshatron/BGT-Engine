package io.joshatron.bgt.engine;

import io.joshatron.bgt.engine.engines.GameEngine;
import io.joshatron.bgt.engine.state.GameState;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import io.joshatron.bgt.engine.action.Action;
import org.apache.commons.lang.SerializationUtils;

import java.util.*;

public class DeterministicGameTree {

    private StateNode root;
    private GameEngine engine;

    public DeterministicGameTree(GameState initialState, GameEngine engine) {
        this.root = new StateNode(initialState);
        this.engine = engine;
    }

    public void executeActionOnRoot(Action action) {
        Optional<StateNode> selected = root.getChildren().stream().filter(node -> node.getState().getLatestAction().equals(action)).findFirst();
        if(selected.isPresent()) {
            root = selected.get();
            root.setParent(null);
        }
        else {
            root = new StateNode(root.getState());
            engine.submitAction(root.getState(), action);
        }
    }

    public StateNode getRootNode() {
        return root;
    }

    private boolean canWin(StateNode node) {
        fillOutChildren(node);
        for(StateNode n : node.getChildren()) {
            if(n.getState().getStatus().isComplete()) {
                return true;
            }
        }

        return false;
    }

    public void fillOutChildren(StateNode node) {
        if(!node.isChildrenFull()) {
            List<Action> actions = engine.getPossibleActions(node.getState());


            actions.parallelStream().map(action -> {
                try {
                    GameState state = (GameState) SerializationUtils.clone(node.getState());
                    engine.submitAction(state, action);
                    return state;
                } catch(BoardGameEngineException e) {
                    return null;
                }
            })
                    .forEach(state -> node.addChild(new StateNode(state)));
            node.setChildrenFull(true);
        }
    }
}
