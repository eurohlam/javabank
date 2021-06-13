package org.mbtest.javabank.fluent;

import org.mbtest.javabank.model.Response;

public class ResponseBuilder implements FluentBuilder {
    private final StubBuilder parent;
    private ResponseTypeBuilder builder;
    private BehaviorBuilder behaviorBuilder;


    protected ResponseBuilder(StubBuilder stubBuilder) {
        this.parent = stubBuilder;
    }

    public IsBuilder is() {
        builder = new IsBuilder(this);
        return (IsBuilder) builder;
    }

    public InjectBuilder inject() {
        builder = new InjectBuilder(this);
        return (InjectBuilder) builder;
    }

    public BehaviorBuilder behaviors() {
        this.behaviorBuilder = new BehaviorBuilder(this);
        return behaviorBuilder;
    }

    @Override
    public StubBuilder end() {
        return parent;
    }

    protected Response build() {
        if (builder != null) {
            Response response = builder.build();
            if (behaviorBuilder != null) {
                response.addBehaviors(behaviorBuilder.build());
            }
            return response;
        }

        return new IsBuilder(this).build();
    }
}
