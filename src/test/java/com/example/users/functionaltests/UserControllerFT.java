package com.example.users.functionaltests;

import com.example.users.UsersServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootTest(
        classes = UsersServiceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@Import(UserControllerFT.TestSecurityConfiguration.class)
@TestPropertySource(properties = "spring.sql.init.mode=always")
class UserControllerFT {

    @Autowired
    private WebTestClient webTestClient;

    @TestConfiguration
    static class TestSecurityConfiguration {
        @Bean
        SecurityFilterChain permitAllRequests(HttpSecurity http) throws Exception {
            return http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                    .csrf(csrf -> csrf.disable())
                    .build();
        }
    }

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
