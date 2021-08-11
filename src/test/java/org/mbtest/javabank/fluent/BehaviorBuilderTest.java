package org.mbtest.javabank.fluent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mbtest.javabank.model.Stub;

public class BehaviorBuilderTest {

    @Test
    void copyBehavior() {
        Stub stub = StubBuilder
                .newInstance()
                .predicate()
                    .equals()
                        .method("GET")
                        .path("/api/v1")
                        .query("id", "1")
                        .header("Content-Type", "application/json")
                    .end()
                .end()
                .response()
                    .is()
                        .statusCode(200)
                        .header("Content-Type", "application/json")
                        .body("{\"clientId\": \"${id}\"}")
                    .end()
                    .behaviors()
                        .copy()
                            .fromQuery("id")
                            .into("${id}")
                            .usingRegex(".+")
                        .end()
                    .end()
                .end()
                .build();

        Assertions.assertNotNull(stub.getResponses().get(0).getBehaviors().get("copy"));
    }

    @Test
    void waitBehavior() {
        Stub stub = StubBuilder
                .newInstance()
                .predicate()
                    .equals()
                        .method("POST")
                        .path("/api/v1")
                        .query("id", "1")
                        .header("Content-Type", "application/json")
                    .end()
                .end()
                .response()
                    .is()
                        .statusCode(200)
                        .header("Content-Type", "application/json")
                        .body("{\"status\": \"ok\"}")
                    .end()
                    .behaviors()
                        .wait_()
                            .withValue(5000)
                        .end()
                    .end()
                .end()
                .build();

        Assertions.assertNotNull(stub.getResponse(0).getBehaviors().get("wait"));
    }

    @Test
    void repeatBehavior() {
        Stub stub = StubBuilder
                .newInstance()
                .predicate()
                    .equals()
                        .method("POST")
                        .path("/api/v1")
                        .query("id", "1")
                        .header("Content-Type", "application/json")
                    .end()
                .end()
                .response()
                    .is()
                        .statusCode(200)
                        .header("Content-Type", "application/json")
                        .body("{\"status\": \"ok\"}")
                    .end()
                    .behaviors()
                        .repeat()
                            .withValue(3)
                        .end()
                    .end()
                .end()
                .build();

        Assertions.assertNotNull(stub.getResponse(0).getBehaviors().get("repeat"));
    }
}
