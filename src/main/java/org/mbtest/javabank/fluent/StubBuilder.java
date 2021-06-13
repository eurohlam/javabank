package org.mbtest.javabank.fluent;

import org.mbtest.javabank.model.Stub;

import java.util.ArrayList;
import java.util.List;

public class StubBuilder implements FluentBuilder {
    private final ImposterBuilder parent;
    private final List<ResponseBuilder> childResponses = new ArrayList<>();
    private final List<PredicateTypeBuilder> childPredicates = new ArrayList<>();

    public static StubBuilder newInstance() {
        return new StubBuilder();
    }

    private StubBuilder() {
        parent = null;
    }

    protected StubBuilder(ImposterBuilder httpImposterBuilder) {
        this.parent = httpImposterBuilder;
    }

    public ResponseBuilder response() {
        ResponseBuilder childResponse = new ResponseBuilder(this);
        childResponses.add(childResponse);
        return childResponse;
    }

    public PredicateTypeBuilder predicate() {
        PredicateTypeBuilder childPredicate = new PredicateTypeBuilder(this);
        childPredicates.add(childPredicate);
        return childPredicate;
    }

    @Override
    public ImposterBuilder end() {
        return parent;
    }

    public Stub build() {
        Stub stub = new Stub();
        childResponses.forEach(response -> stub.addResponse(response.build()));
        childPredicates.forEach(predicate -> stub.addPredicates(predicate.build()));
        return stub;
    }
}
