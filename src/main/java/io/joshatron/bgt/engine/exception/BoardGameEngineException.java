package io.joshatron.bgt.engine.exception;

public class BoardGameEngineException extends Exception {
    private BoardGameErrorCode code;

    public BoardGameEngineException(BoardGameErrorCode code) {
        super("The board game engine encountered an exception of type: " + code.getName());
        this.code = code;
    }

    public BoardGameErrorCode getCode() {
        return code;
    }
}
