package com.ai.common.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

/**
 * Unit tests for the ai-specific problem details.
 */
@DisplayName("GlobalExceptionHandler")
class GlobalExceptionHandlerTest {

  private GlobalExceptionHandler handler;

  @BeforeEach
  void setUp() {
    handler = new GlobalExceptionHandler();
  }

  @Test
  @DisplayName("should return 401 with the anonymous id problem type when identity is missing")
  void shouldReturn401WithTheAnonymousIdProblemTypeWhenIdentityIsMissing() {
    ProblemDetail problem = handler.handleAnonymousIdRequired(new AnonymousIdRequiredException());

    assertThat(problem.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    assertThat(problem.getType())
        .isEqualTo(URI.create("https://www.felixzhu.chat/problems/client-identity-required"));
    assertThat(problem.getDetail()).isEqualTo("Anonymous id required");
  }

  @Test
  @DisplayName("should return 400 with the argument message when an argument is invalid")
  void shouldReturn400WithTheArgumentMessageWhenAnArgumentIsInvalid() {
    ProblemDetail problem =
        handler.handleIllegalArgument(new IllegalArgumentException("Invalid input"));

    assertThat(problem.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    assertThat(problem.getType())
        .isEqualTo(URI.create("https://www.felixzhu.chat/problems/bad-request"));
    assertThat(problem.getDetail()).isEqualTo("Invalid input");
  }

  @Test
  @DisplayName("should return 503 without the database message when storage fails")
  void shouldReturn503WithoutTheDatabaseMessageWhenStorageFails() {
    ProblemDetail problem =
        handler.handleDataAccessError(
            new DataAccessResourceFailureException("jdbc:postgresql://db/secret refused"));

    assertThat(problem.getStatus()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE.value());
    assertThat(problem.getType())
        .isEqualTo(URI.create("https://www.felixzhu.chat/problems/chat-memory-error"));
    assertThat(problem.getDetail()).doesNotContain("jdbc");
  }

  @Test
  @DisplayName("should return 500 without the exception message when an error is unexpected")
  void shouldReturn500WithoutTheExceptionMessageWhenAnErrorIsUnexpected() {
    ProblemDetail problem =
        handler.handleGenericException(new RuntimeException("NullPointer at secret.Internal"));

    assertThat(problem.getStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
    assertThat(problem.getType())
        .isEqualTo(URI.create("https://www.felixzhu.chat/problems/internal-error"));
    assertThat(problem.getDetail()).isEqualTo("An unexpected error occurred");
  }
}
