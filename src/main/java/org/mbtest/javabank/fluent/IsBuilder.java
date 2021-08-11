package org.mbtest.javabank.fluent;

import org.mbtest.javabank.model.Is;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


public class IsBuilder extends ResponseTypeBuilder {
    private int statusCode = 200;
    private String body = "";
    private String mode;
    private File bodyFile;
    private final Map<String, String> headers = new HashMap<>();

    protected IsBuilder(ResponseBuilder responseBuilder) {
        super(responseBuilder);
    }

    public IsBuilder statusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public IsBuilder header(String name, String value) {
        headers.put(name, value);
        return this;
    }

    public IsBuilder body(String body) {
        this.body = body;
        return this;
    }

    public IsBuilder body(File body) {
        this.bodyFile = body;
        return this;
    }

    public IsBuilder mode(String mode) {
        this.mode = mode;
        return this;
    }

    @Override
    protected Is build() {

        if (this.bodyFile != null) {
            try {
                byte[] bytes = Files.readAllBytes(this.bodyFile.toPath());
                this.body = new String(bytes, Charset.defaultCharset());
            } catch (IOException e) {
                this.body = null;
            }
        }

        return new Is()
                .withStatusCode(statusCode)
                .withHeaders(headers)
                .withBody(body)
                .withMode(mode);
    }
}
