package com.example.users.functionaltests;

import com.example.users.UsersServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(
        classes = UsersServiceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class UserControllerFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void returnsUsersFromSeededDatabase() {
        webTestClient.get()
                .uri("/api/users?search=madrid")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.totalElements").isEqualTo(1)
                .jsonPath("$.content[0].firstName").isEqualTo("Ana")
                .jsonPath("$.content[0].billable").isEqualTo(true);
    }

    @Test
    void filtersNonBillableUsersFromSeededDatabase() {
        webTestClient.get()
                .uri("/api/users?billable=false")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.totalElements").isEqualTo(1)
                .jsonPath("$.content[0].email").isEqualTo("gabriela.torres@example.com")
                .jsonPath("$.content[0].billable").isEqualTo(false);
    }
}
