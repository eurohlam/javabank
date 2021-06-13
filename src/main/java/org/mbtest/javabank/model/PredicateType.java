package org.mbtest.javabank.model;

public enum PredicateType {
    EQUALS("equals"),
    DEEP_EQUALS("deepEquals"),
    CONTAINS("contains"),
    STARTS_WITH("startsWith"),
    ENDS_WITH("endsWith"),
    MATCHES("matches"),
    EXISTS("exists"),
    NOT("not"),
    OR("or"),
    AND("and"),
    INJECT("inject");

    private String value;

    PredicateType(String value) {

        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
