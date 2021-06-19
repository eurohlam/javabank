package org.mbtest.javabank.model;

public enum ProtocolType {

    HTTP("http"),
    HTTPS("https");

    private String protocol;

    ProtocolType(String protocol) {
        this.protocol = protocol;
    }

    public String getValue() {
        return this.protocol;
    }
}
