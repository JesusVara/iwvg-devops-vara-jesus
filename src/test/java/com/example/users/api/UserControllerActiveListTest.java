package com.example.users.api;

import com.example.users.config.SecurityConfig;
import com.example.users.domain.Role;
import com.example.users.service.UserService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
class UserControllerActiveListTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void updatesActiveUsersWithValidCsrfToken() throws Exception {
        String csrfToken = "test-csrf-token";
        UserResponse response = new UserResponse(
                28L, "Ana", "García", "ana@example.com", "ID-28",
                "Calle Mayor 1", "Madrid", "Madrid", "28001", Role.USER, true, true);
        when(userService.updateUsersActive(anyList())).thenReturn(List.of(response));

        mockMvc.perform(patch("/user")
                        .cookie(new Cookie("XSRF-TOKEN", csrfToken))
                        .header("X-XSRF-TOKEN", csrfToken)
                        .contentType("application/json")
                        .content("""
                                [
                                  {"id": 28, "active": true},
                                  {"id": 29, "active": false}
                                ]
                                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(content().json("""
                        [
                          {"id": 28, "active": true}
                        ]
                        """));

        verify(userService).updateUsersActive(anyList());
    }

    @Test
    void rejectsActiveListUpdateWithoutCsrfToken() throws Exception {
        mockMvc.perform(patch("/user")
                        .contentType("application/json")
                        .content("[{\"id\":28,\"active\":true}]"))
                .andExpect(status().isForbidden());
    }

    @Test
    void rejectsActiveListUpdateWithIncompleteRequest() throws Exception {
        String csrfToken = "test-csrf-token";

        mockMvc.perform(patch("/user")
                        .cookie(new Cookie("XSRF-TOKEN", csrfToken))
                        .header("X-XSRF-TOKEN", csrfToken)
                        .contentType("application/json")
                        .content("[{\"id\":28}]"))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(userService);
    }
}
