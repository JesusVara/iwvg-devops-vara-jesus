package com.example.users.api;

import com.example.users.config.SecurityConfig;
import com.example.users.service.UserService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
class UserControllerActivateTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void activatesUserWithValidCsrfToken() throws Exception {
        String csrfToken = "test-csrf-token";

        mockMvc.perform(put("/user/28/active")
                        .cookie(new Cookie("XSRF-TOKEN", csrfToken))
                        .header("X-XSRF-TOKEN", csrfToken))
                .andExpect(status().isNoContent());

        verify(userService).activateUser(28L);
    }

    @Test
    void rejectsActivationWithoutCsrfToken() throws Exception {
        mockMvc.perform(put("/user/28/active"))
                .andExpect(status().isForbidden());
    }
}
