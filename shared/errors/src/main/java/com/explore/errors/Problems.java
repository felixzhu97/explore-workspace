package com.explore.errors;

import java.net.URI;
import java.util.Locale;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;

/**
 * Builds RFC 9457 problem details whose {@code type} is derived from a stable error code.
 */
public final class Problems {

  /** Path prefix of every problem type this platform defines. */
  public static final String TYPE_PREFIX = "/problems/";

  private Problems() {}

  /**
   * Creates a problem detail for an error code.
   *
   * @param status the HTTP status
   * @param code the stable error code, in {@code UPPER_SNAKE_CASE}
   * @param detail the client-safe description
   * @return the problem detail; Spring MVC fills in {@code instance} from the request path
   */
  public static ProblemDetail of(HttpStatusCode status, String code, String detail) {
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
    problem.setType(typeOf(code));
    return problem;
  }

  /**
   * Creates the problem detail for a domain error.
   *
   * @param exception the domain error
   * @return the problem detail with the status for its kind
   */
  public static ProblemDetail of(DomainException exception) {
    return of(statusOf(exception.getKind()), exception.getCode(), exception.getMessage());
  }

  /**
   * Returns the problem type for an error code, such as {@code /problems/session-not-found}.
   *
   * @param code the stable error code, in {@code UPPER_SNAKE_CASE}
   * @return the relative problem type URI
   */
  public static URI typeOf(String code) {
    return URI.create(TYPE_PREFIX + code.toLowerCase(Locale.ROOT).replace('_', '-'));
  }

  /**
   * Maps each kind of domain error to its HTTP status.
   *
   * @param kind what went wrong
   * @return the HTTP status for that kind
   */
  public static HttpStatus statusOf(DomainException.Kind kind) {
    return switch (kind) {
      case NOT_FOUND -> HttpStatus.NOT_FOUND;
      case FORBIDDEN -> HttpStatus.FORBIDDEN;
      case CONFLICT -> HttpStatus.CONFLICT;
      case INVALID -> HttpStatus.BAD_REQUEST;
      case LIMIT_EXCEEDED -> HttpStatus.TOO_MANY_REQUESTS;
      case UNPROCESSABLE -> HttpStatus.UNPROCESSABLE_CONTENT;
      case UNAVAILABLE -> HttpStatus.SERVICE_UNAVAILABLE;
      case FAILED -> HttpStatus.INTERNAL_SERVER_ERROR;
    };
  }
}
