package com.explore.errors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Returns every {@link DomainException} as an RFC 9457 problem detail.
 *
 * <p>Runs just before an application's catch-all advice, which uses the default lowest precedence;
 * endpoint-scoped advice such as SCIM error handling should use a higher precedence.
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class DomainExceptionHandler {

  /**
   * Handles a domain error.
   *
   * @param exception the domain error
   * @return the problem detail with the status for its kind
   */
  @ExceptionHandler(DomainException.class)
  public ProblemDetail handleDomainException(DomainException exception) {
    return Problems.of(exception);
  }
}
