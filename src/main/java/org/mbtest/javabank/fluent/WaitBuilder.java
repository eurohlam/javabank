package org.mbtest.javabank.fluent;

import org.mbtest.javabank.model.Behavior;
import org.mbtest.javabank.model.Wait;

public class WaitBuilder extends BehaviorTypeBuilder {

    private int milliseconds = 0;
    private String function;

    protected WaitBuilder(BehaviorBuilder parent) {
        super(parent);
    }

    public WaitBuilder withValue(int milliseconds) {
        this.milliseconds = milliseconds;
        return this;
    }

    public WaitBuilder withFunction(String function) {
        this.function = function;
        return this;
    }

    @Override
    protected Behavior build() {
        Wait wait = new Wait();
        if (function != null) {
            wait.withFunction(function);
        } else {
            wait.withValue(milliseconds);
        }

        return wait;
    }
}
