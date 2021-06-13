package org.mbtest.javabank.model;

public class Inject extends Response {
    static final long serialVersionUID = 42L;

    private static final String INJECT = "inject";

    public Inject() {
        super();
        this.put(INJECT, "");
    }

    public Inject withFunction(String function) {
        this.put(INJECT, function);
        return this;
    }
}
