package org.mbtest.javabank.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Copy extends Behavior {

    static final long serialVersionUID = 42L;

    private static final BehaviorType COPY = BehaviorType.COPY;
    private static final String FROM = "from";
    private static final String INTO = "into";
    private static final String USING = "using";
    private static final String USING_METHOD = "method";
    private static final String USING_SELECTOR = "selector";
    private static final String USING_NAMESPACE = "ns";

    public Copy(){

    }

    public Copy withFromPath() {
        this.put(FROM, "path");
        return this;
    }

    public Copy withFromBody() {
        this.put(FROM, "body");
        return this;
    }

    public Copy withFromQuery(String queryParameter) {
        this.put(FROM, Collections.singletonMap("query", queryParameter));
        return this;
    }

    public Copy withFromHeader(String headerName) {
        this.put(FROM, Collections.singletonMap("headers", headerName));
        return this;
    }

    public Copy withInto(String into) {
        this.put(INTO, into);
        return this;
    }

    public Copy withRegex(String regex) {
        Map<String, String> using = new HashMap<>();
        using.put(USING_METHOD, "regex");
        using.put(USING_SELECTOR, regex);
        this.put(USING, using);
        return this;
    }

    public Copy withXpath(String xpath, Map<String, String> namespaces) {
        Map<String, Object> using = new HashMap<>();
        using.put(USING_METHOD, "xpath");
        using.put(USING_SELECTOR, xpath);
        using.put(USING_NAMESPACE, namespaces);
        this.put(USING, using);
        return this;
    }

    public Copy withJsonpath(String jsonpath) {
        Map<String, String> using = new HashMap<>();
        using.put(USING_METHOD, "jsonpath");
        using.put(USING_SELECTOR, jsonpath);
        this.put(USING, using);
        return this;
    }


    @Override
    public BehaviorType getType() {
        return COPY;
    }
}
