package com.finpulse.server.portfolio.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.finpulse.server.portfolio.dto.PortfolioRequest;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.json.JsonMapper;

@SpringBootTest
@AutoConfigureMockMvc
class PortfolioControllerTest {
  @Autowired private MockMvc mockMvc;
  @Autowired private JsonMapper objectMapper;

  @Test
  void shouldCreatePortfolio() throws Exception {
    PortfolioRequest request =
        PortfolioRequest.builder()
            .accountId(UUID.randomUUID())
            .name("Growth")
            .baseCurrency("USD")
            .build();
    mockMvc
        .perform(
            post("/api/v1/portfolios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.portfolio_id").isNotEmpty())
        .andExpect(jsonPath("$.name").value("Growth"));
  }
}
