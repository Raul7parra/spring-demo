package es.fplumara.dam2.springdemo.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class NotesJwtSecurityTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    MockMvc mockMvc;

    private String loginAndGetToken(String username, String password) throws Exception {
        var result = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                  {"username":"%s","password":"%s"}
                                """.formatted(username, password)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andReturn();

        String body = result.getResponse().getContentAsString();
        JsonNode json = objectMapper.readTree(body);
        return json.get("accessToken").asText();
    }

    @Test
    void notes_without_token_is_401() throws Exception {
        mockMvc.perform(get("/notes"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void user_token_can_get_notes() throws Exception {
        String token = loginAndGetToken("user", "user123");

        mockMvc.perform(get("/notes")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void user_token_cannot_post_notes_is_403() throws Exception {
        String token = loginAndGetToken("user", "user123");

        mockMvc.perform(post("/notes")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"nota user\"}"))
                .andExpect(status().isForbidden());
    }

    @Test
    void admin_token_can_post_notes() throws Exception {
        String token = loginAndGetToken("admin", "admin123");

        mockMvc.perform(post("/notes")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"nota admin\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.text").value("nota admin"));
    }
}
