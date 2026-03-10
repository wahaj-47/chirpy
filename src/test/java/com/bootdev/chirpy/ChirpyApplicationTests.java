package com.bootdev.chirpy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class ChirpyApplicationTests {

	@Autowired
	RestTestClient restTestClient;

	@Test
	void shouldReturnUserWhenDataIsSaved() {
		restTestClient.get()
				.uri("/api/users/e11cda83-a6b2-4035-8c43-474af4c5efeb")
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody()
				.jsonPath("$.id")
				.isEqualTo("e11cda83-a6b2-4035-8c43-474af4c5efeb")
				.jsonPath("$.email")
				.isEqualTo("test@email.com")
				.jsonPath("$.hashedPassword")
				.exists()
				.jsonPath("$.createdAt")
				.exists()
				.jsonPath("$.updatedAt")
				.exists();
	}
}
