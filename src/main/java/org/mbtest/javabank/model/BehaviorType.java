package org.mbtest.javabank.model;

public enum BehaviorType {

    WAIT("wait"),
    COPY("copy"),
    DECORATE("decorate"),
    LOOKUP("lookup");

    private String value;

    BehaviorType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
