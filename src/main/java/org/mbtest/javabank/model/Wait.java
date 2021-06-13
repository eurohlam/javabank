package org.mbtest.javabank.model;

import java.util.Optional;

public class Wait extends Behavior {

    static final long serialVersionUID = 42L;

    private static final BehaviorType WAIT = BehaviorType.WAIT;

    private int value = 0;
    private String function;

    public Wait() {
    }

    public Wait withValue(int value) {
        this.value = value;
        return this;
    }

    public Wait withFunction(String function) {
        this.function = function;
        return this;
    }

    public int getValue() {
        return value;
    }

    public Optional<String> getFunction() {
        return Optional.ofNullable(function);
    }

    @Override
    public BehaviorType getType() {
        return WAIT;
    }
}
