package com.bootdev.chirpy;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
public class ChirpJsonTest {

    @Autowired
    private JacksonTester<Chirp> tester;

    @Test
    public void testSerialize() throws IOException {
        // TODO: Implement testSerialize
    }

    @Test
    public void testDeserialize() throws IOException {
        // TODO: Implement testDeserialize
    }
}
