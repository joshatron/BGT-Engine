package io.joshatron.bgt.engine.component;

import io.joshatron.bgt.engine.board.BoardLocation;
import io.joshatron.bgt.engine.board.BoardTile;
import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
public class PieceStack<T extends BoardLocation,P extends Piece> extends BoardTile<T> {

    //from bottom to top
    private ArrayList<P> pieces;

    public PieceStack(T location) {
        super(location);
        pieces = new ArrayList<>();
    }

    public void addPieces(List<P> pieces) {
        this.pieces.addAll(pieces);
    }

    public void addPiece(P piece) {
        pieces.add(piece);
    }

    public List<P> removePieces(int toRemove) throws BoardGameEngineException {
        if(toRemove > pieces.size()) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.TOO_MANY_PIECES_TO_REMOVE);
        }

        int pieceLoc = pieces.size() - toRemove;
        List<P> removed = new ArrayList<>();
        for(int i = 0; i < toRemove; i++) {
            removed.add(pieces.remove(pieceLoc));
        }

        return removed;
    }

    public P getTopPiece() {
        if(pieces.isEmpty()) {
            return null;
        }
        return pieces.get(pieces.size() - 1);
    }

    public List<P> getTopPieces(int num) throws BoardGameEngineException {
        if(num > pieces.size()) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.TOO_MANY_PIECES_TO_REMOVE);
        }

        List<P> top = new ArrayList<>();
        for(int i = 0; i < num; i++) {
            top.add(pieces.get(pieces.size() - num + i));
        }

        return top;
    }

    public int getHeight() {
        return pieces.size();
    }

    public boolean isEmpty() {
        return pieces.size() == 0;
    }

    //Prints top to bottom
    @Override
    public String toString() {
        if(pieces.isEmpty()) {
            return "";
        }

        StringBuilder str = new StringBuilder();

        for(int i = pieces.size() - 1; i >= 0; i--) {
            str.append(pieces.get(i).toString());
        }

        return str.toString();
    }
}
