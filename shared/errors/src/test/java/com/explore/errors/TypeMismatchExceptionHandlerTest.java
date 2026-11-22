package com.explore.errors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@DisplayName("TypeMismatchExceptionHandler")
class TypeMismatchExceptionHandlerTest {

  private final MockMvc mockMvc =
      MockMvcBuilders.standaloneSetup(new DocumentController())
          .setControllerAdvice(new TypeMismatchExceptionHandler())
          .build();

  @Test
  @DisplayName("should name the parameter without echoing the value when its type is wrong")
  void shouldNameTheParameterWithoutEchoingTheValueWhenItsTypeIsWrong() throws Exception {
    mockMvc
        .perform(get("/documents/alice@example.com"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.type").value("https://www.felixzhu.chat/problems/invalid-parameter"))
        .andExpect(jsonPath("$.detail").value("Parameter 'documentId' has an invalid value"));
  }

  @RestController
  static class DocumentController {

    @GetMapping("/documents/{documentId}")
    String find(@PathVariable UUID documentId) {
      return documentId.toString();
    }
  }
}
