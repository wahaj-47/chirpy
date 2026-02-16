package com.bootdev.chirpy;

import static org.assertj.core.api.Assertions.assertThat;

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
        Chirp chirp = new Chirp(1L, 1L, "Hello Chirpy!", "2022-01-01T00:00:00Z");

        assertThat(tester.write(chirp)).isStrictlyEqualToJson("chirp.json");
        assertThat(tester.write(chirp)).hasJsonPathNumberValue("@.id");
        assertThat(tester.write(chirp)).hasJsonPathNumberValue("@.userId");
        assertThat(tester.write(chirp)).hasJsonPathStringValue("@.body");
        assertThat(tester.write(chirp)).hasJsonPathStringValue("@.createdAt");

        assertThat(tester.write(chirp)).extractingJsonPathNumberValue("@.id").isEqualTo(1);
        assertThat(tester.write(chirp)).extractingJsonPathNumberValue("@.userId").isEqualTo(1);
        assertThat(tester.write(chirp)).extractingJsonPathStringValue("@.body").isEqualTo("Hello Chirpy!");
        assertThat(tester.write(chirp)).extractingJsonPathStringValue("@.createdAt").isEqualTo("2022-01-01T00:00:00Z");
    }

    @Test
    public void testDeserialize() throws IOException {
        String expected = """
                {
                  "id": 1,
                  "userId": 1,
                  "body": "Hello Chirpy!",
                  "createdAt": "2022-01-01T00:00:00Z"
                }
                """;

        assertThat(tester.parse(expected)).isEqualTo(new Chirp(1L, 1L, "Hello Chirpy!", "2022-01-01T00:00:00Z"));
        assertThat(tester.parseObject(expected).id()).isEqualTo(1);
        assertThat(tester.parseObject(expected).userId()).isEqualTo(1);
        assertThat(tester.parseObject(expected).body()).isEqualTo("Hello Chirpy!");
        assertThat(tester.parseObject(expected).createdAt()).isEqualTo("2022-01-01T00:00:00Z");
    }
}
