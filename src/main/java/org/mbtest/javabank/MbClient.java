package org.mbtest.javabank;

import org.mbtest.javabank.model.Imposter;
import org.mbtest.javabank.model.Stub;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MbClient {

    private static final Logger LOG = Logger.getLogger(MbClient.class.getName());

    private final String mountebankAdminUrl;
    private final HttpClient.Builder builder;
    private final int imposterPort;

    public static MbClient newInstance(final String mountebankAdminUrl, final int imposterPort) {
        return new MbClient(mountebankAdminUrl, imposterPort);
    }

    protected MbClient(final String mountebankAdminUrl, final int imposterPort) {
        this.mountebankAdminUrl = mountebankAdminUrl + "/imposters/";
        this.imposterPort = imposterPort;
        builder = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20));
    }

    public MbClient createImposter(Imposter imposter) {
        LOG.log(Level.INFO, "Creating a new MB-imposter: " + imposter);
        imposter.onPort(imposterPort);
        HttpRequest request = HttpRequest
                .newBuilder()
                .setHeader("Content-Type", "application/json")
                .uri(URI.create(mountebankAdminUrl))
                .POST(HttpRequest.BodyPublishers.ofString(imposter.toString()))
                .build();
        String response = send(request);
        LOG.log(Level.INFO, "Response from MB: " + response);
        return this;
    }

    public MbClient deleteImposter() {
        LOG.log(Level.INFO, "Deleting MB-imposter on port: " + imposterPort);
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(mountebankAdminUrl + imposterPort))
                .DELETE()
                .build();
        String response = send(request);
        LOG.log(Level.INFO, "Response from MB: " + response);
        return this;
    }

    public MbClient addStub(Stub stub) {
        return addStub(stub.toString(), 0);
    }

    private MbClient addStub(String stub, int index) {
        String json = "{\"index\": " + index + ", \"stub\": " + stub + "}";
        LOG.log(Level.INFO, "Adding MB-stub: " + json);
        HttpRequest request = HttpRequest
                .newBuilder()
                .setHeader("Content-Type", "application/json")
                .uri(URI.create(mountebankAdminUrl + imposterPort + "/stubs"))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        String response = send(request);
        LOG.log(Level.INFO, "Response from MB: " + response);
        return this;
    }

    private String send(HttpRequest request) {
        try {
            HttpClient client = builder.build();
            HttpResponse<String> r = client.send(request, HttpResponse.BodyHandlers.ofString());
            return r.body();
        } catch (Exception e) {
            LOG.severe(e.getMessage());
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }
}
