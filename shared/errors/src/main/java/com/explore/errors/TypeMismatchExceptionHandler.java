package com.explore.errors;

import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Returns request values of the wrong type as problem details that name the parameter only.
 *
 * <p>Runs before Spring Boot's problem details handler, whose detail echoes the rejected value.
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE + 100)
public class TypeMismatchExceptionHandler {

  /**
   * Handles a request value that cannot be converted to its parameter type.
   *
   * @param exception the conversion failure
   * @return problem detail with type {@code /problems/invalid-parameter}
   */
  @ExceptionHandler(TypeMismatchException.class)
  public ProblemDetail handleTypeMismatch(TypeMismatchException exception) {
    String name =
        exception instanceof MethodArgumentTypeMismatchException argument
            ? argument.getName()
            : exception.getPropertyName();
    String detail =
        name == null
            ? "A parameter has an invalid value"
            : "Parameter '" + name + "' has an invalid value";
    return Problems.of(HttpStatus.BAD_REQUEST, "INVALID_PARAMETER", detail);
  }
}
