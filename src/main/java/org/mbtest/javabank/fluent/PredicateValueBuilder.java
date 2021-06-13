package org.mbtest.javabank.fluent;

import org.mbtest.javabank.model.Predicate;
import org.mbtest.javabank.model.PredicateType;

public class PredicateValueBuilder implements FluentBuilder {
    private final Predicate predicate;
    private final PredicateTypeBuilder parent;

    public PredicateValueBuilder(PredicateTypeBuilder predicateTypeBuilder, PredicateType type) {
        predicate = new Predicate(type);
        parent = predicateTypeBuilder;
    }

    public PredicateValueBuilder method(String method) {
        predicate.withMethod(method);
        return this;
    }

    public PredicateValueBuilder path(String path) {
        predicate.withPath(path);
        return this;
    }

    public PredicateValueBuilder query(String parameter, String value) {
        predicate.addQueryParameter(parameter, value);
        return this;
    }

    public PredicateValueBuilder body(String body) {
        predicate.withBody(body);
        return this;
    }

    public PredicateValueBuilder header(String name, String value) {
        predicate.addHeader(name, value);
        return this;
    }

    @Override
    public PredicateTypeBuilder end() {
        return parent;
    }

    public Predicate build() {
        return predicate;
    }
}
