package org.mbtest.javabank.fluent;

import org.mbtest.javabank.model.Response;

public abstract class ResponseTypeBuilder implements FluentBuilder {
    private final ResponseBuilder parent;

    protected ResponseTypeBuilder(ResponseBuilder parent) {
        this.parent = parent;
    }

    @Override
    public ResponseBuilder end() {
        return parent;
    }

    protected abstract Response build();
}
