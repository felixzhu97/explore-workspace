package com.ai.common.controller;

import com.explore.errors.Problems;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Maps ai-specific failures to RFC 9457 problem details.
 *
 * <p>Domain errors are handled by the shared {@code DomainExceptionHandler}, and standard Spring
 * MVC exceptions by Spring Boot's problem details handler ({@code spring.mvc.problemdetails}).
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  /** Returns 401 when the request has no Client Identity. */
  @ExceptionHandler(ClientIdentityRequiredException.class)
  public ProblemDetail handleClientIdentityRequired(ClientIdentityRequiredException e) {
    return Problems.of(
        HttpStatus.UNAUTHORIZED, "CLIENT_IDENTITY_REQUIRED", "Client identity required");
  }

  /** Returns 400 for an invalid argument. */
  @ExceptionHandler(IllegalArgumentException.class)
  public ProblemDetail handleIllegalArgument(IllegalArgumentException e) {
    return Problems.of(HttpStatus.BAD_REQUEST, "BAD_REQUEST", e.getMessage());
  }

  /** Returns 503 when the database fails. */
  @ExceptionHandler(DataAccessException.class)
  public ProblemDetail handleDataAccessError(DataAccessException e) {
    return Problems.of(
        HttpStatus.SERVICE_UNAVAILABLE,
        "CHAT_MEMORY_ERROR",
        "Chat memory storage is temporarily unavailable");
  }

  /** Returns 500 for any other error, without exposing its message. */
  @ExceptionHandler(Exception.class)
  public ProblemDetail handleGenericException(Exception e) {
    log.error("Unhandled request failure", e);
    return Problems.of(
        HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", "An unexpected error occurred");
  }
}
