package com.iam.common.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@DisplayName("ApiExceptionHandler")
class ApiExceptionHandlerTest {

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(new RejectingController())
            .setControllerAdvice(new ApiExceptionHandler())
            .build();
  }

  @Test
  @DisplayName("should return an RFC 9457 problem detail when an argument is invalid")
  void shouldReturnAnRfc9457ProblemDetailWhenAnArgumentIsInvalid() throws Exception {
    mockMvc
        .perform(get("/clients"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
        .andExpect(jsonPath("$.type").value("/problems/invalid-request"))
        .andExpect(jsonPath("$.title").value("Bad Request"))
        .andExpect(jsonPath("$.status").value(400))
        .andExpect(jsonPath("$.detail").value("Redirect URI must use https"))
        .andExpect(jsonPath("$.instance").value("/clients"));
  }

  @RestController
  static class RejectingController {

    @GetMapping("/clients")
    String create() {
      throw new IllegalArgumentException("Redirect URI must use https");
    }
  }
}
