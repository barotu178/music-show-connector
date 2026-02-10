package org.obaro.portfolio.musicshow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.obaro.portfolio.musicshow.dto.ShowRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ShowControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // --------------------
    // READ ALL (pagination + wrapper)
    // --------------------
    @Test
    void shouldReturnPaginatedShows() throws Exception {
        mockMvc.perform(get("/shows")
                        .param("page", "0")
                        .param("size", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.page").value(0))
                .andExpect(jsonPath("$.size").value(3))
                .andExpect(jsonPath("$.totalElements").exists())
                .andExpect(jsonPath("$.totalPages").exists());
    }
    @Test
    void whenInvalidShowRequest_thenReturns400() throws Exception {
        String invalidPayload = """
        {
          "artist": "",
          "showDate": null,
          "venue": "",
          "city": ""
        }
        """;

        mockMvc.perform(post("/shows")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPayload))
                .andExpect(status().isBadRequest());
    }


    // --------------------
    // SORTING
    // --------------------
    @Test
    void shouldSortShowsByArtistDesc() throws Exception {
        mockMvc.perform(get("/shows")
                        .param("sort", "artist,desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].artist").exists());
    }

    // --------------------
    // CREATE (valid)
    // --------------------
    @Test
    void shouldCreateShow() throws Exception {
        ShowRequest request = new ShowRequest();
        request.setArtist("Test Artist");
        request.setVenue("Test Venue");
        request.setCity("Test City");
        request.setShowDate(java.time.LocalDate.of(2026, 6, 1));

        mockMvc.perform(post("/shows")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.artist").value("Test Artist"));
    }

    // --------------------
    // CREATE (validation error)
    // --------------------
    @Test
    void shouldFailValidationWhenArtistMissing() throws Exception {
        ShowRequest request = new ShowRequest();
        request.setVenue("Venue");
        request.setCity("City");

        mockMvc.perform(post("/shows")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    // --------------------
    // READ ONE (404)
    // --------------------
    @Test
    void shouldReturn404WhenShowNotFound() throws Exception {
        mockMvc.perform(get("/shows/{id}", 99999))
                .andExpect(status().isNotFound());
    }
}
