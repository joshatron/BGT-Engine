package io.joshatron.bgt.engine.player;

import io.joshatron.bgt.engine.exception.BoardGameCommonErrorCode;
import io.joshatron.bgt.engine.exception.BoardGameEngineException;

public enum PlayerIndicator {
    BLACK("b"),
    WHITE("w"),
    RED("r"),
    BLUE("bu"),
    GREEN("g"),
    YELLOW("y"),
    PURPLE("p"),
    PINK("pi"),
    ORANGE("o"),
    BROWN("br"),
    GREY("gy"),
    PLAYER_0("0"),
    PLAYER_1("1"),
    PLAYER_2("2"),
    PLAYER_3("3"),
    PLAYER_4("4"),
    PLAYER_5("5"),
    PLAYER_6("6"),
    PLAYER_7("7"),
    PLAYER_8("8"),
    PLAYER_9("9"),
    PLAYER_10("10"),
    PLAYER_11("11"),
    PLAYER_12("12"),
    PLAYER_13("13"),
    PLAYER_14("14"),
    PLAYER_15("15"),
    PLAYER_16("16"),
    PLAYER_17("17"),
    PLAYER_18("18"),
    PLAYER_19("19"),
    PLAYER_20("20"),
    NONE("n");

    private String acronym;

    PlayerIndicator(String acronym) {
        this.acronym = acronym;
    }

    public String getAcronym() {
        return acronym;
    }

    public PlayerIndicator fromString(String player) throws BoardGameEngineException {
        for(PlayerIndicator playerIndicator : PlayerIndicator.values()) {
            if(playerIndicator.acronym.equalsIgnoreCase(player)) {
                return playerIndicator;
            }
        }

        throw new BoardGameEngineException(BoardGameCommonErrorCode.INVALID_PLAYER);
    }
}
