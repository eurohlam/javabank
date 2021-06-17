package org.mbtest.javabank.fluent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mbtest.javabank.model.Stub;

public class StubBuilderTest {

    @Test
    void createStub() {
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
                .response()
                    .is()
                        .statusCode(404)
                    .end()
                .end()
                .build();

        Assertions.assertEquals(stub.getResponses().size(), 2);

    }
}
