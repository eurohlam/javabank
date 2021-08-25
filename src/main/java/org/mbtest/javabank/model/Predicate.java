package org.mbtest.javabank.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Predicate extends MbEntity {

    static final long serialVersionUID = 42L;

    private static final String PATH = "path";
    private static final String METHOD = "method";
    private static final String QUERY = "query";
    private static final String HEADERS = "headers";
    private static final String REQUEST_FROM = "requestFrom";
    private static final String BODY = "body";

    //predicate parameters
    private static final String CASE_SENSITIVE  = "caseSensitive";
    private static final String XPATH = "xpath";
    private static final String JSONPATH = "jsonpath";
    private static final String EXCEPT = "except";

    private final Map<String, Object> data;
    private final PredicateType type;

    public Predicate(PredicateType type) {
        this.type = type;
        data = new HashMap<>();
        this.put(type.getValue(), data);
    }

    private Predicate addEntry(String key, String value) {
        data.put(key, value);
        return this;
    }

    private Predicate addEntry(String key, Map<String, String> map) {
        data.put(key, map);
        return this;
    }

    private Predicate addMapEntry(String key, String name, String value) {
        if (!data.containsKey(key)) {
            data.put(key, new HashMap<>());
        }

        @SuppressWarnings("unchecked")
        Map<String, String> entryMap = (Map<String, String>) data.get(key);
        entryMap.put(name, value);

        return this;
    }

    private Object getEntry(String key) {
        return this.data.get(key);
    }

    @Override
    public String toString() {
        return toJson().toJSONString();
    }

    public String getType() {
        return type.getValue();
    }

    public Predicate withPath(String path) {
        addEntry(PATH, path);
        return this;
    }

    public Predicate withMethod(String method) {
        addEntry(METHOD, method);
        return this;
    }

    public Predicate withQueryParameters(Map<String, String> parameters) {
        addEntry(QUERY, parameters);
        return this;
    }

    public Predicate addQueryParameter(String name, String value) {
        addMapEntry(QUERY, name, value);
        return this;
    }

    public Predicate addHeader(String name, String value) {
        addMapEntry(HEADERS, name, value);
        return this;
    }

    public Predicate withRequestFrom(String host, String port) {
        addEntry(REQUEST_FROM, host + ":" + port);
        return this;
    }

    public Predicate withBody(String body) {
        addEntry(BODY, body);
        return this;
    }

    public Predicate withCaseSensitive(boolean caseSensitive) {
        this.put(CASE_SENSITIVE, caseSensitive);
        return this;
    }
    public Predicate withXpath(String xpath) {
        this.put(XPATH, Collections.singletonMap("selector", xpath));
        return this;
    }
    public Predicate withJsonpath(String jsonpath) {
        this.put(JSONPATH, Collections.singletonMap("selector", jsonpath));
        return this;
    }
    public Predicate withExcept(String except) {
        this.put(EXCEPT, except);
        return this;
    }


    public String getPath() {
        return (String) getEntry(PATH);
    }

    public String getMethod() {
        return (String) getEntry(METHOD);
    }

    public String getRequestFrom() {
        return (String) getEntry(REQUEST_FROM);
    }

    public String getBody() {
        return (String) getEntry(BODY);
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getQueryParameters() {
        return (Map<String, String>) getEntry(QUERY);
    }

    public String getQueryParameter(String parameter) {
        return getQueryParameters().get(parameter);
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getHeaders() {
        return (Map<String, String>) getEntry(HEADERS);
    }

    public String getHeader(String name) {
        return getHeaders().get(name);
    }

    public boolean isCaseSensitive() {
        return (boolean)this.getOrDefault(CASE_SENSITIVE, false);
    }

    public String getExcept() {
        return (String)this.getOrDefault(EXCEPT, "");
    }

    @SuppressWarnings("unchecked")
    public String getJsonpath() {
        return ((Map<String, String>)this.getOrDefault(JSONPATH, Collections.singletonMap("selector", ""))).get("selector");
    }

    @SuppressWarnings("unchecked")
    public String getXpath() {
        return ((Map<String, String>)this.getOrDefault(XPATH, Collections.singletonMap("selector", ""))).get("selector");
    }
}
