package es.upm.miw.devops.functionaltests;

import es.upm.miw.devops.code.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class UserResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindByIdExist() {
        webTestClient.get()
                .uri("/user/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .value(user -> {
                    assertThat(user.getId()).isEqualTo("1");
                    assertThat(user.getName()).isEqualTo("Oscar");
                    assertThat(user.getFamilyName()).isEqualTo("Fernandez");
                });
    }

    @Test
    void testFindByIdNotFound() {
        webTestClient.get()
                .uri("/user/999")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testFindByIdAnaBlanco() {
        webTestClient.get()
                .uri("/user/2")
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .value(user -> {
                    assertThat(user.getId()).isEqualTo("2");
                    assertThat(user.getName()).isEqualTo("Ana");
                    assertThat(user.getFamilyName()).isEqualTo("Blanco");
                    assertThat(user.getFractions()).hasSize(4);
                });
    }
}
