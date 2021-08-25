package org.mbtest.javabank.fluent;

import org.mbtest.javabank.model.Predicate;
import org.mbtest.javabank.model.PredicateType;

import java.util.ArrayList;
import java.util.List;

public class PredicateTypeBuilder implements FluentBuilder {

    private final StubBuilder parent;

    private final List<PredicateValueBuilder> childPredicateValueBuilders = new ArrayList<>();

    private boolean caseSensitive = false;
    private String jsonpath = "";
    private String xpath = "";
    private String except = "";

    protected PredicateTypeBuilder(StubBuilder stubBuilder) {
        this.parent = stubBuilder;
    }

    public PredicateValueBuilder equals() {
        return getHttpMatcherBuilder(PredicateType.EQUALS);
    }

    public PredicateValueBuilder deepEquals() {
        return getHttpMatcherBuilder(PredicateType.DEEP_EQUALS);
    }

    public PredicateValueBuilder contains() {
        return getHttpMatcherBuilder(PredicateType.CONTAINS);
    }

    public PredicateValueBuilder startsWith() {
        return getHttpMatcherBuilder(PredicateType.STARTS_WITH);
    }

    public PredicateValueBuilder endsWith() {
        return getHttpMatcherBuilder(PredicateType.ENDS_WITH);
    }

    public PredicateValueBuilder matches() {
        return getHttpMatcherBuilder(PredicateType.MATCHES);
    }

    public PredicateValueBuilder exists() {
        return getHttpMatcherBuilder(PredicateType.EXISTS);
    }

    public PredicateValueBuilder not() {
        return getHttpMatcherBuilder(PredicateType.NOT);
    }

    public PredicateValueBuilder or() {
        return getHttpMatcherBuilder(PredicateType.OR);
    }

    public PredicateValueBuilder inject() {
        return getHttpMatcherBuilder(PredicateType.INJECT);
    }

    public PredicateValueBuilder and() {
        return getHttpMatcherBuilder(PredicateType.AND);
    }

    private PredicateValueBuilder getHttpMatcherBuilder(PredicateType type) {
        PredicateValueBuilder predicateValueBuilder = new PredicateValueBuilder(this, type);
        childPredicateValueBuilders.add(predicateValueBuilder);
        return predicateValueBuilder;
    }

    public PredicateTypeBuilder caseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
        return this;
    }

    public PredicateTypeBuilder jsonpath(String jsonpath) {
        this.jsonpath = jsonpath;
        return this;
    }

    public PredicateTypeBuilder xpath(String xpath) {
        this.xpath = xpath;
        return this;
    }

    public PredicateTypeBuilder except(String except) {
        this.except = except;
        return this;
    }

    @Override
    public StubBuilder end() {
        return parent;
    }

    protected List<Predicate> build() {
        List<Predicate> predicates = new ArrayList<>();
        for (PredicateValueBuilder predicateValueBuilder : childPredicateValueBuilders) {
            Predicate predicate = predicateValueBuilder.build();
            if (caseSensitive) {
                predicate.withCaseSensitive(true);
            }
            if (jsonpath != null && jsonpath.trim().length() != 0) {
                predicate.withJsonpath(jsonpath);
            }
            if (xpath != null && xpath.trim().length() != 0) {
                predicate.withXpath(xpath);
            }
            if (except != null && except.trim().length() != 0) {
                predicate.withExcept(except);
            }
            predicates.add(predicate);
        }
        return predicates;
    }
}
