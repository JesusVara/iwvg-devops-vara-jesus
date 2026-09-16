package com.example.users.functionaltests;

import com.example.users.UsersServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootTest(
        classes = UsersServiceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("test")
@Import(UserControllerFT.TestSecurityConfiguration.class)
@TestPropertySource(properties = "spring.sql.init.mode=always")
class UserControllerFT {

    @Autowired

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
    }

    @Test
    }
}
