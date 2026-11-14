package com.ai.common.controller;

import com.explore.errors.Problems;
import java.util.stream.Collectors;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

/**
 * Returns validation failures as problem details that name each invalid field.
 *
 * <p>Runs before Spring Boot's problem details handler, whose detail omits field names.
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 100)
public class ValidationExceptionHandler {

  private static final String CODE = "VALIDATION_ERROR";

  /** Returns 400 with the invalid request body fields. */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ProblemDetail handleInvalidBody(MethodArgumentNotValidException e) {
    String detail =
        e.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining(", "));
    return Problems.of(HttpStatus.BAD_REQUEST, CODE, detail);
  }

  /** Returns 400 with the invalid request parameters. */
  @ExceptionHandler(HandlerMethodValidationException.class)
  public ProblemDetail handleInvalidParameters(HandlerMethodValidationException e) {
    String detail =
        e.getParameterValidationResults().stream()
            .flatMap(
                result ->
                    result.getResolvableErrors().stream()
                        .map(
                            error ->
                                result.getMethodParameter().getParameterName()
                                    + ": "
                                    + error.getDefaultMessage()))
            .collect(Collectors.joining(", "));
    return Problems.of(HttpStatus.BAD_REQUEST, CODE, detail);
  }
}
