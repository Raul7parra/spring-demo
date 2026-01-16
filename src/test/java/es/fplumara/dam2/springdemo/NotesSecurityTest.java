package es.fplumara.dam2.springdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NotesSecurityTest {

    @Autowired MockMvc mockMvc;

    @Test
    void get_notes_without_auth_is_401() throws Exception {
        mockMvc.perform(get("/notes"))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser(username = "user", roles = {"USER"})
    @Test
    void user_can_get_notes() throws Exception {
        mockMvc.perform(get("/notes"))
                .andExpect(status().isOk());
    }

    @WithMockUser(username = "user", roles = {"USER"})
    @Test
    void user_cannot_post_notes() throws Exception {
        mockMvc.perform(post("/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"nota\"}"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(username = "admin", roles = {"ADMIN"})
    @Test
    void admin_can_post_notes() throws Exception {
        mockMvc.perform(post("/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"nota admin\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }
}
