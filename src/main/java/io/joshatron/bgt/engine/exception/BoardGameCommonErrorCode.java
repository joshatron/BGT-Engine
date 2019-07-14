package io.joshatron.bgt.engine.exception;

public enum BoardGameCommonErrorCode implements BoardGameErrorCode {
    OFF_BOARD,
    ILLEGAL_TURN,
    INVALID_BOARD_INITIALIZATION,
    INVALID_TYPE,
    INVALID_PLAYER,
    NOT_ENOUGH_PIECES,
    GAME_FINISHED;

    @Override
    public String getName() {
        return this.name();
    }
}
