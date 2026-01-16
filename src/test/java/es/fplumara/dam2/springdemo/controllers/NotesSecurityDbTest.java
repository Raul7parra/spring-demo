package es.fplumara.dam2.springdemo.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NotesSecurityDbTest {

    @Autowired MockMvc mockMvc;

    @Test
    void user_can_get_notes() throws Exception {
        mockMvc.perform(get("/notes").with(httpBasic("user", "user123")))
                .andExpect(status().isOk());
    }

    @Test
    void user_cannot_post_notes() throws Exception {
        mockMvc.perform(post("/notes")
                        .with(httpBasic("user", "user123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"nota\"}"))
                .andExpect(status().isForbidden());
    }

    @Test
    void admin_can_post_notes() throws Exception {
        mockMvc.perform(post("/notes")
                        .with(httpBasic("admin", "admin123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"nota admin\"}"))
                .andExpect(status().isOk());
    }
}
