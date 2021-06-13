package org.mbtest.javabank.fluent;

import org.mbtest.javabank.model.Behavior;
import org.mbtest.javabank.model.Copy;

import java.util.Map;

public class CopyBuilder extends BehaviorTypeBuilder {

    private enum From {
        BODY,
        QUERY,
        PATH,
        HEADER
    }

    private From from;
    private String queryParameter;
    private String headerName;
    private String into;
    private String regex;
    private String xpath;
    private Map<String, String> namespaces;
    private String jsonpath;

    protected CopyBuilder(BehaviorBuilder parent) {
        super(parent);
    }

    @Override
    protected Behavior build() {
        Copy copy = new Copy()
                .withInto(into);

        switch (from) {
            case BODY:
                copy.withFromBody();
                break;
            case QUERY:
                copy.withFromQuery(queryParameter);
                break;
            case PATH:
                copy.withFromPath();
                break;
            case HEADER:
                copy.withFromHeader(headerName);
                break;
            default:
                break;
        }

        if (regex != null) {
            copy.withRegex(regex);
        } else if (xpath != null) {
            copy.withXpath(xpath, namespaces);
        } else if (jsonpath != null) {
            copy.withJsonpath(jsonpath);
        }
        return copy;
    }

    public CopyBuilder fromPath() {
        this.from = From.PATH;
        return this;
    }

    public CopyBuilder fromBody() {
        this.from = From.BODY;
        return this;
    }

    public CopyBuilder fromQuery(String queryParameter) {
        this.from = From.QUERY;
        this.queryParameter = queryParameter;
        return this;
    }

    public CopyBuilder fromHeader(String headerName) {
        this.from = From.HEADER;
        this.headerName = headerName;
        return this;
    }

    public CopyBuilder into(String into) {
        this.into = into;
        return this;
    }

    public CopyBuilder usingRegex(String regex) {
        this.regex = regex;
        return this;
    }

    public CopyBuilder usingXpath(String xpath, Map<String, String> namespaces) {
        this.xpath = xpath;
        this.namespaces = namespaces;
        return this;
    }

    public CopyBuilder usingJsonpath(String jsonpath) {
        this.jsonpath = jsonpath;
        return this;
    }

}
