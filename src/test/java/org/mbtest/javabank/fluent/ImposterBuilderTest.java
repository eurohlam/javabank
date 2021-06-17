package org.mbtest.javabank.fluent;

import org.junit.jupiter.api.Test;
import org.mbtest.javabank.model.Imposter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImposterBuilderTest {

    @Test
    void createEmptyImposter() {
        Imposter imposter = ImposterBuilder
                .anImposter()
                .onPort(5555)
                .protocol("https")
                .build();

        assertEquals(imposter.toJson().get("protocol"), "https");
        assertEquals(imposter.toJson().get("port"), 5555);
    }
}
