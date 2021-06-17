package org.mbtest.javabank;


import org.junit.jupiter.api.Test;

public class MbClientTest {

    @Test
    void createImposter(){
        MbClient
                .newInstance("http://localhost:2525", 7777, "/test")
                .createImposter();
    }
}
