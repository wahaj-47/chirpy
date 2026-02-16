package com.bootdev.chirpy;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
public class UserJsonTest {

    @Autowired
    private JacksonTester<User> tester;

    @Test
    public void testSerialize() throws IOException {
        User user = new User(1L, "test@test.com", "password", "2022-01-01T00:00:00Z");

        assertThat(tester.write(user)).isStrictlyEqualToJson("user.json");
        assertThat(tester.write(user)).hasJsonPathNumberValue("@.id");
        assertThat(tester.write(user)).hasJsonPathStringValue("@.email");
        assertThat(tester.write(user)).hasJsonPathStringValue("@.password");
        assertThat(tester.write(user)).hasJsonPathStringValue("@.createdAt");

        assertThat(tester.write(user)).extractingJsonPathNumberValue("@.id").isEqualTo(1);
        assertThat(tester.write(user)).extractingJsonPathStringValue("@.email").isEqualTo("test@test.com");
        assertThat(tester.write(user)).extractingJsonPathStringValue("@.password").isEqualTo("password");
        assertThat(tester.write(user)).extractingJsonPathStringValue("@.createdAt").isEqualTo("2022-01-01T00:00:00Z");
    }

    @Test
    public void testDeserialize() throws IOException {
        String expected = """
                {
                  "id": 1,
                  "email": "test@test.com",
                  "password": "password",
                  "createdAt": "2022-01-01T00:00:00Z"
                }
                """;

        assertThat(tester.parse(expected)).isEqualTo(new User(1L, "test@test.com", "password", "2022-01-01T00:00:00Z"));
        assertThat(tester.parseObject(expected).id()).isEqualTo(1);
        assertThat(tester.parseObject(expected).email()).isEqualTo("test@test.com");
        assertThat(tester.parseObject(expected).password()).isEqualTo("password");
        assertThat(tester.parseObject(expected).createdAt()).isEqualTo("2022-01-01T00:00:00Z");
    }
}
