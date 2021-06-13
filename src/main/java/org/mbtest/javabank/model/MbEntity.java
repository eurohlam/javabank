package org.mbtest.javabank.model;

import org.json.simple.JSONObject;

import java.util.HashMap;

public abstract class MbEntity extends HashMap<String, Object> {

    static final long serialVersionUID = 42L;

    public JSONObject toJson() {
        return new JSONObject(this);
    }

    @Override
    public String toString() {
        return toJson().toJSONString();
    }

}
