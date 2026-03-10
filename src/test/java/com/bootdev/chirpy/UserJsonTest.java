package com.bootdev.chirpy;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

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
        UUID id = UUID.fromString("1a2b3c4d-1a2b-3c4d-1a2b-3c4d1a2b3c4d");
        Instant createdAt = Instant.parse("2022-01-01T00:00:00Z");
        Instant updatedAt = Instant.parse("2022-01-01T00:00:00Z");

        User user = new User(id, "test@test.com", "password", createdAt, updatedAt);

        assertThat(tester.write(user)).isStrictlyEqualToJson("user.json");
        assertThat(tester.write(user)).hasJsonPathStringValue("@.id");
        assertThat(tester.write(user)).hasJsonPathStringValue("@.email");
        assertThat(tester.write(user)).hasJsonPathStringValue("@.hashedPassword");
        assertThat(tester.write(user)).hasJsonPathStringValue("@.createdAt");
        assertThat(tester.write(user)).hasJsonPathStringValue("@.updatedAt");

        assertThat(tester.write(user)).extractingJsonPathStringValue("@.id").isEqualTo(id.toString());
        assertThat(tester.write(user)).extractingJsonPathStringValue("@.email").isEqualTo("test@test.com");
        assertThat(tester.write(user)).extractingJsonPathStringValue("@.hashedPassword").isEqualTo("password");
        assertThat(tester.write(user)).extractingJsonPathStringValue("@.createdAt").isEqualTo("2022-01-01T00:00:00Z");
        assertThat(tester.write(user)).extractingJsonPathStringValue("@.updatedAt").isEqualTo("2022-01-01T00:00:00Z");
    }

    @Test
    public void testDeserialize() throws IOException {
        UUID id = UUID.fromString("1a2b3c4d-1a2b-3c4d-1a2b-3c4d1a2b3c4d");
        Instant createdAt = Instant.parse("2022-01-01T00:00:00Z");
        Instant updatedAt = Instant.parse("2022-01-01T00:00:00Z");

        String expected = String.format("""
                        {
                            "id": "%s",
                            "email": "test@test.com",
                            "hashedPassword": "password",
                            "createdAt": "2022-01-01T00:00:00Z",
                            "updatedAt": "2022-01-01T00:00:00Z"
                        }
                """, id);

        User expectedUser = new User(id, "test@test.com", "password", createdAt, updatedAt);

        assertThat(tester.parse(expected)).isEqualTo(expectedUser);

        User parsedUser = tester.parseObject(expected);
        assertThat(parsedUser.id()).isEqualTo(id);
        assertThat(parsedUser.email()).isEqualTo("test@test.com");
        assertThat(parsedUser.hashedPassword()).isEqualTo("password");
        assertThat(parsedUser.createdAt()).isEqualTo(createdAt);
        assertThat(parsedUser.updatedAt()).isEqualTo(updatedAt);
    }
}
