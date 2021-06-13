package org.mbtest.javabank.model;

import java.util.ArrayList;
import java.util.List;


public class Stub extends MbEntity {

    static final long serialVersionUID = 42L;

    private static final String RESPONSES = "responses";
    private static final String PREDICATES = "predicates";

    private List<Response> responses;
    private List<Predicate> predicates;

    public Stub() {
        responses = new ArrayList<>();
        predicates = new ArrayList<>();
        this.put(RESPONSES, responses);
        this.put(PREDICATES, predicates);
    }

    public Stub withResponse(Response response) {
        getResponses().clear();
        getResponses().add(response);

        return this;
    }

    public Stub addResponse(Response response) {
        getResponses().add(response);
        return this;
    }

    public Stub addPredicates(List<Predicate> predicates) {
        getPredicates().addAll(predicates);
        return this;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public List<Predicate> getPredicates() {
        return predicates;
    }

    public MbEntity getResponse(int index) {
        return getResponses().get(index);
    }

    public Predicate getPredicate(int index) {
        return getPredicates().get(index);
    }
}
