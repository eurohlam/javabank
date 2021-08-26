package org.mbtest.javabank.model;

import java.util.Map;

public abstract class Response extends MbEntity {
    static final long serialVersionUID = 42L;

    private static final String BEHAVIORS = "_behaviors";

    private Map<String,Object> behaviors;

    public Response(){
    }

    public Response addBehaviors(Map<String, Object> behaviors) {
        this.behaviors = behaviors;
        this.put(BEHAVIORS, this.behaviors);
        return this;
    }

    public Map<String,Object> getBehaviors() {
        return behaviors;
    }

    public Response withRepeat(int repeat) {
        this.put("repeat", repeat);
        return this;
    }

    public int getRepeat() {
        return (int) this.getOrDefault("repeat", 1);
    }

}
