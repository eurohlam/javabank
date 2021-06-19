package org.mbtest.javabank.model;

import java.util.ArrayList;
import java.util.List;

public class Imposter extends MbEntity {

    static final long serialVersionUID = 42L;

    private static final String PORT = "port";
    private static final String PROTOCOL = "protocol";
    private static final String STUBS = "stubs";

    private List<Stub> stubs;

    public Imposter() {
        this.put(PROTOCOL, "http");
        stubs = new ArrayList<>();
        this.put(STUBS, stubs);
    }

    public static Imposter anImposter() {
        return new Imposter();
    }

    public Imposter onPort(int port) {
        this.put(PORT, port);
        return this;
    }

    public Imposter addStub(Stub stub) {
        getStubs().add(stub);
        return this;
    }

    public Imposter withProtocol(ProtocolType protocol) {
        this.put("protocol", protocol.getValue());
        return this;
    }

    public Imposter withName(String name) {
        this.put("name", name);
        return this;
    }

    public Imposter withRecordRequests(boolean recordRequests) {
        this.put("recordRequests", recordRequests);
        return this;
    }

    public Imposter withDefaultResponse(String response) {
        this.put("defaultResponse", response);
        return this;
    }

    public Imposter withAllowCors(boolean allowCors) {
        this.put("allowCORS", allowCors);
        return this;
    }

    public Imposter withSslPrivateKey(String pem) {
        this.put("key", pem);
        return this;
    }

    public Imposter withSslCertificate(String pem) {
        this.put("cert", pem);
        return this;
    }

    public Imposter withMutualAuth(boolean mutualAuth) {
        this.put("mutualAuth", mutualAuth);
        return this;
    }

    public Imposter withStub(Stub stub) {
        stubs = new ArrayList<>();
        addStub(stub);
        this.put(STUBS, stubs);
        return this;
    }

    public List<Stub> getStubs() {
        return stubs;
    }

    public Stub getStub(int index) {
        return getStubs().get(index);
    }


    @Override
    public String toString() {
        return toJson().toJSONString();
    }

    public int getPort() {
        return Integer.parseInt(String.valueOf(this.get(PORT)));
    }
}
