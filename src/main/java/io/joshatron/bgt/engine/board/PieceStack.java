package io.joshatron.bgt.engine.board;

import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
public class PieceStack extends BoardTile {

    //from bottom to top
    private ArrayList<Piece> pieces;

    public PieceStack(BoardLocation location) {
        super(location);
        pieces = new ArrayList<>();
    }

    public void addPieces(List<Piece> pieces) {
        this.pieces.addAll(pieces);
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public List<Piece> removePieces(int toRemove) throws BoardGameEngineException {
        if(toRemove > pieces.size()) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.TOO_MANY_PIECES_TO_REMOVE);
        }

        int pieceLoc = pieces.size() - toRemove;
        ArrayList<Piece> removed = new ArrayList<>();
        for(int i = 0; i < toRemove; i++) {
            removed.add(pieces.remove(pieceLoc));
        }

        return removed;
    }

    public Piece getTopPiece() {
        if(pieces.isEmpty()) {
            return null;
        }
        return pieces.get(pieces.size() - 1);
    }

    public List<Piece> getTopPieces(int num) throws BoardGameEngineException {
        if(num > pieces.size()) {
            throw new BoardGameEngineException(BoardGameCommonErrorCode.TOO_MANY_PIECES_TO_REMOVE);
        }

        ArrayList<Piece> top = new ArrayList<>();
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

    // Prints top to bottom according to tak by mail rules
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
