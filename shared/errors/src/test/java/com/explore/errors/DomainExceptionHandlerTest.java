package com.explore.errors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@DisplayName("DomainExceptionHandler")
class DomainExceptionHandlerTest {

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(new FailingController())
            .setControllerAdvice(new DomainExceptionHandler())
            .build();
  }

  @Test
  @DisplayName("should return every RFC 9457 field when a domain error is thrown")
  void shouldReturnEveryRfc9457FieldWhenADomainErrorIsThrown() throws Exception {
    mockMvc
        .perform(get("/orders/NOT_FOUND"))
        .andExpect(status().isNotFound())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
        .andExpect(jsonPath("$.type").value("/problems/order-not-found"))
        .andExpect(jsonPath("$.title").value("Not Found"))
        .andExpect(jsonPath("$.status").value(404))
        .andExpect(jsonPath("$.detail").value("Order was not found"))
        .andExpect(jsonPath("$.instance").value("/orders/NOT_FOUND"));
  }

  @ParameterizedTest(name = "{0} -> {1}")
  @CsvSource({
    "NOT_FOUND, 404",
    "FORBIDDEN, 403",
    "CONFLICT, 409",
    "INVALID, 400",
    "LIMIT_EXCEEDED, 429",
    "UNPROCESSABLE, 422",
    "UNAVAILABLE, 503",
    "FAILED, 500"
  })
  @DisplayName("should map each kind to its status")
  void shouldMapEachKindToItsStatus(String kind, int status) throws Exception {
    mockMvc
        .perform(get("/orders/" + kind))
        .andExpect(status().is(status))
        .andExpect(jsonPath("$.status").value(status));
  }

  @RestController
  static class FailingController {

    @GetMapping("/orders/{kind}")
    String find(@PathVariable DomainException.Kind kind) {
      throw new DomainException(kind, "ORDER_NOT_FOUND", "Order was not found");
    }
  }
}
