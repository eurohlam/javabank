package org.mbtest.javabank.fluent;

import org.mbtest.javabank.model.Imposter;
import org.mbtest.javabank.model.ProtocolType;

import java.util.ArrayList;
import java.util.List;


public class ImposterBuilder {
    private int port;
    private ProtocolType protocol;
    private List<StubBuilder> stubBuilders = new ArrayList<>();
    private String name = "";
    private boolean recordRequests = false;
    private boolean allowCors = false;
    private String defaultResponse;
    private String sslPrivateKey;
    private String sslCertificate;
    private boolean mutualAuth = false;

    public static ImposterBuilder anImposter() {
        return new ImposterBuilder();
    }

    public ImposterBuilder onPort(int port) {
        this.port = port;
        return this;
    }

    public ImposterBuilder protocol(ProtocolType protocol) {
        this.protocol = protocol;
        return this;
    }

    public ImposterBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ImposterBuilder recordRequests(boolean recordRequests) {
        this.recordRequests = recordRequests;
        return this;
    }

    public ImposterBuilder allowCors(boolean allowCors) {
        this.allowCors = allowCors;
        return this;
    }

    public ImposterBuilder defaultResponse(String defaultResponse) {
        this.defaultResponse = defaultResponse;
        return this;
    }

    public ImposterBuilder sslPrivateKey(String privateKey) {
        this.sslPrivateKey = privateKey;
        return this;
    }

    public ImposterBuilder sslCertificate(String sslCertificate) {
        this.sslCertificate = sslCertificate;
        return this;
    }

    public StubBuilder stub() {
        StubBuilder child = new StubBuilder(this);
        stubBuilders.add(child);
        return child;
    }

    public ImposterBuilder mutualAuth(boolean mutualAuth) {
        this.mutualAuth = mutualAuth;
        return this;
    }

    public Imposter build() {
        Imposter imposter = new Imposter()
                .onPort(port)
                .withProtocol(protocol)
                .withName(name)
                .withRecordRequests(recordRequests);
        for (StubBuilder stubBuilder : stubBuilders) {
            imposter.addStub(stubBuilder.build());
        }

        if (defaultResponse != null) {
            imposter.withDefaultResponse(defaultResponse);
        }

        if (protocol == ProtocolType.HTTPS || protocol == ProtocolType.HTTP) {
            imposter.withAllowCors(allowCors);
        }

        if (protocol == ProtocolType.HTTPS) {
            imposter.withMutualAuth(mutualAuth);
            if (sslPrivateKey != null) {
                imposter.withSslPrivateKey(sslPrivateKey);
            }
            if (sslCertificate != null) {
                imposter.withSslCertificate(sslCertificate);
            }
        }
        return imposter;
    }
}
