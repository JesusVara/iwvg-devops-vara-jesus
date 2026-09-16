package com.example.users.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(UserService.class)
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void findsSeededUsersBySearch() {
        var result = userService.findUsers("madrid", null, PageRequest.of(0, 20));

        assertThat(result.getContent()).extracting("firstName").containsExactly("Ana");
    }

    @Test
    void filtersSeededUsersByBillableStatus() {
        var result = userService.findUsers(null, false, PageRequest.of(0, 20));

        assertThat(result.getContent()).extracting("email")
                .containsExactly("gabriela.torres@example.com");
    }
}
