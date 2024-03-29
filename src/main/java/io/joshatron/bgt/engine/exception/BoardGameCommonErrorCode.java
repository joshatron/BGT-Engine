package io.joshatron.bgt.engine.exception;

public enum BoardGameCommonErrorCode implements BoardGameErrorCode {
    OFF_BOARD,
    ILLEGAL_TURN,
    INVALID_BOARD_INITIALIZATION,
    INVALID_TYPE,
    INVALID_PLAYER,
    NOT_ENOUGH_PIECES,
    INVALID_DIRECTION,
    INVALID_NUMBER,
    INVALID_PIECE,
    IN_ORDER_QUESTION_ONLY,
    TOO_MANY_PIECES_TO_REMOVE,
    INVALID_INPUT,
    GAME_FINISHED,
    NOT_IMPLEMENTED;

    @Override
    public String getName() {
        return this.name();
    }
}
