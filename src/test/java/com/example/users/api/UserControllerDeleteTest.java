package com.example.users.api;

import com.example.users.config.SecurityConfig;
import com.example.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
class UserControllerDeleteTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void deletesUserWithValidCsrfToken() throws Exception {
        mockMvc.perform(delete("/user/28").with(csrf()))
                .andExpect(status().isNoContent());

        verify(userService).deleteUser(28L);
    }

    @Test
    void rejectsDeleteWithoutCsrfToken() throws Exception {
        mockMvc.perform(delete("/user/28"))
                .andExpect(status().isForbidden());
    }
}
