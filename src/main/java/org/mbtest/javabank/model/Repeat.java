package org.mbtest.javabank.model;

public class Repeat extends Behavior {

    static final long serialVersionUID = 42L;

    private int value = 0;

    public Repeat withValue(int value) {
        if (value >= 0) {
            this.value = value;
        }
        return this;
    }

    public int getValue() {
        return value;
    }

    @Override
    public BehaviorType getType() {
        return BehaviorType.REPEAT;
    }
}
