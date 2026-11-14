package com.explore.errors;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * Registers the shared error handlers in servlet web applications.
 */
@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class ErrorsAutoConfiguration {

  /**
   * Returns the shared domain error handler.
   *
   * @return the handler
   */
  @Bean
  @ConditionalOnMissingBean
  public DomainExceptionHandler domainExceptionHandler() {
    return new DomainExceptionHandler();
  }

  /**
   * Returns the handler that keeps rejected request values out of error responses.
   *
   * @return the handler
   */
  @Bean
  @ConditionalOnMissingBean
  public TypeMismatchExceptionHandler typeMismatchExceptionHandler() {
    return new TypeMismatchExceptionHandler();
  }
}
