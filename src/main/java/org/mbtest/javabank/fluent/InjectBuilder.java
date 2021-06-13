package org.mbtest.javabank.fluent;

import org.mbtest.javabank.model.Inject;

public class InjectBuilder extends ResponseTypeBuilder {
    private String function = "";

    protected InjectBuilder(ResponseBuilder responseBuilder) {
        super(responseBuilder);
    }

    public InjectBuilder function(String function) {
        this.function = function;
        return this;
    }

    @Override
    protected Inject build() {
        return new Inject().withFunction(function);
    }
}
