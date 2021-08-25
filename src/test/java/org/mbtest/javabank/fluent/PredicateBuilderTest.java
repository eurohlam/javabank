package org.mbtest.javabank.fluent;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mbtest.javabank.model.Stub;

public class PredicateBuilderTest {

    @Test
    void predicate() {
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
                .predicate()
                    .equals()
                        .body("any")
                    .end()
                    .jsonpath("$.clientType")
                    .caseSensitive(true)
                .end()
                .response()
                    .is()
                        .statusCode(200)
                    .end()
                .end()
                .build();

        Assertions.assertEquals(stub.getPredicates().size(), 2);
        Assertions.assertEquals(stub.getPredicate(0).getMethod(), "POST");
        Assertions.assertTrue(stub.getPredicate(1).isCaseSensitive());
        Assertions.assertEquals(stub.getPredicate(1).getJsonpath(), "$.clientType");
        Assertions.assertEquals(stub.getPredicate(0).getXpath(), "");
    }
}
