package io.joshatron.bgt.engine.state;

public interface Turn {
    Turn createFromString(String turn);
    Turn makeCopy();
}
