package org.mbtest.javabank.fluent;

import org.mbtest.javabank.model.Imposter;

import java.util.ArrayList;
import java.util.List;


public class ImposterBuilder {
    private int port;
    private String protocol;
    private List<StubBuilder> stubBuilders = new ArrayList<>();

    public static ImposterBuilder anImposter() {
        return new ImposterBuilder();
    }

    public ImposterBuilder onPort(int port) {
        this.port = port;
        return this;
    }

    public ImposterBuilder protocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public StubBuilder stub() {
        StubBuilder child = new StubBuilder(this);
        stubBuilders.add(child);
        return child;
    }

    public Imposter build() {
        Imposter imposter = new Imposter()
                .onPort(port)
                .withProtocol(protocol);
        for (StubBuilder stubBuilder : stubBuilders) {
            imposter.addStub(stubBuilder.build());
        }
        return imposter;
    }
}
