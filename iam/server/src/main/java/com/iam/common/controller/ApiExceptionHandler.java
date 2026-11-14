package com.iam.common.controller;

import com.explore.errors.Problems;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Maps domain validation failures to RFC 9457 problem details.
 */
@RestControllerAdvice
public class ApiExceptionHandler {

  /**
   * Handles illegal arguments as HTTP 400 responses.
   *
   * @param ex the validation failure
   * @return problem detail with type {@code /problems/invalid-request}
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ProblemDetail handleIllegalArgument(IllegalArgumentException ex) {
    return Problems.of(HttpStatus.BAD_REQUEST, "INVALID_REQUEST", ex.getMessage());
  }
}
