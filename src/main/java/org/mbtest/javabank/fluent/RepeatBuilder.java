package org.mbtest.javabank.fluent;

import org.mbtest.javabank.model.Behavior;
import org.mbtest.javabank.model.Repeat;

public class RepeatBuilder extends BehaviorTypeBuilder {

    private int value;

    protected RepeatBuilder(BehaviorBuilder parent) {
        super(parent);
    }

    public RepeatBuilder withValue(int value) {
        this.value = value;
        return this;
    }

    @Override
    protected Behavior build() {
        return new Repeat().withValue(value);
    }
}
