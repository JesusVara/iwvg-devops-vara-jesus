package com.example.users.functionaltests;

import com.example.users.UsersServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        classes = UsersServiceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(properties = "spring.sql.init.mode=always")
class UserControllerFT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsBillableUsersFromSeededDatabase() throws Exception {
        mockMvc.perform(get("/api/users").param("billable", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(7))
                .andExpect(jsonPath("$.content[0].billable").value(true));
    }

    @Test
    void returnsNonBillableUsersFromSeededDatabase() throws Exception {
        mockMvc.perform(get("/api/users").param("billable", "false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.content[0].email").value("gabriela.torres@example.com"))
                .andExpect(jsonPath("$.content[0].billable").value(false));
    }

}
