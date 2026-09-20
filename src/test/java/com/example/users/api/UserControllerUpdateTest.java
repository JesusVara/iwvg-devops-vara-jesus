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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
class UserControllerUpdateTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void updatesUserWithValidCsrfToken() throws Exception {
        String csrfToken = "test-csrf-token";
        UserResponse response = new UserResponse(
                28L, "Ana", "García", "ana.updated@example.com", "ID-28",
                "Calle Mayor 1", "Madrid", "Madrid", "28001", Role.USER, true, false);
        when(userService.updateUser(eq(28L), any(UserUpdateRequest.class))).thenReturn(response);

        mockMvc.perform(put("/user/28")
                        .cookie(new Cookie("XSRF-TOKEN", csrfToken))
                        .header("X-XSRF-TOKEN", csrfToken)
                        .contentType("application/json")
                        .content("""
                                {
                                  "firstName": "Ana",
                                  "familyName": "García",
                                  "email": "ana.updated@example.com",
                                  "identity": "ID-28",
                                  "address": "Calle Mayor 1",
                                  "city": "Madrid",
                                  "province": "Madrid",
                                  "postalCode": "28001",
                                  "role": "USER",
                                  "active": false
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(content().json("""
                        {
                          "id": 28,
                          "email": "ana.updated@example.com",
                          "active": false
                        }
                        """));

        verify(userService).updateUser(eq(28L), any(UserUpdateRequest.class));
    }

    @Test
    void rejectsUpdateWithoutCsrfToken() throws Exception {
        mockMvc.perform(put("/user/28")
                        .contentType("application/json")
                        .content("{}"))
                .andExpect(status().isForbidden());
    }

    @Test
    void rejectsUpdateWithMissingFields() throws Exception {
        String csrfToken = "test-csrf-token";

        mockMvc.perform(put("/user/28")
                        .cookie(new Cookie("XSRF-TOKEN", csrfToken))
                        .header("X-XSRF-TOKEN", csrfToken)
                        .contentType("application/json")
                        .content("""
                                {
                                  "firstName": "Ana",
                                  "email": "ana.updated@example.com"
                                }
                                """))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(userService);
    }
}
