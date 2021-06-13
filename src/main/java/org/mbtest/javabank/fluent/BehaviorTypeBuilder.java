package org.mbtest.javabank.fluent;

import org.mbtest.javabank.model.Behavior;

public abstract class BehaviorTypeBuilder implements FluentBuilder {

    private final BehaviorBuilder parent;

    protected BehaviorTypeBuilder(BehaviorBuilder parent) {
        this.parent = parent;
    }

    @Override
    public BehaviorBuilder end() {
        return parent;
    }

    protected abstract Behavior build();
}
