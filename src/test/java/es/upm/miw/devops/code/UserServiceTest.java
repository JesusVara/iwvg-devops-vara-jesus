package es.upm.miw.devops.code;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {
    private final UserService userService = new UserService();

    @Test
    void testFindByIdExist() {
        Optional<User> user = userService.findById("1");
        assertThat(user)
                .isPresent()
                .get()
                .satisfies(u -> {
                    assertThat(u.getId()).isEqualTo("1");
                    assertThat(u.getName()).isEqualTo("Oscar");
                    assertThat(u.getFamilyName()).isEqualTo("Fernandez");
                });
    }

    @Test
    void testFindByIdNotExist() {
        Optional<User> user = userService.findById("999");
        assertThat(user).isEmpty();
    }

    @Test
    void testFindByIdWithFractions() {
        Optional<User> user = userService.findById("2");
        assertThat(user)
                .isPresent()
                .get()
                .satisfies(u -> {
                    assertThat(u.getId()).isEqualTo("2");
                    assertThat(u.getName()).isEqualTo("Ana");
                    assertThat(u.getFractions()).hasSize(4);
                });
    }
}
