package com.explore.provisioning;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverters;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Registers the shared SCIM filter translator and, in servlet web applications, the SCIM JSON
 * converter ahead of the default Jackson converter.
 */
@AutoConfiguration
public class ProvisioningAutoConfiguration {

  /**
   * Returns the SCIM filter translator.
   *
   * @return the translator
   */
  @Bean
  @ConditionalOnMissingBean
  public ScimFilterTranslator scimFilterTranslator() {
    return new ScimFilterTranslator();
  }

  /**
   * Adds the SCIM JSON converter to Spring MVC.
   */
  @Configuration(proxyBeanMethods = false)
  @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
  static class ScimWebMvcConfiguration {

    /**
     * Returns the MVC configurer that adds the SCIM JSON converter.
     *
     * @return the configurer
     */
    @Bean
    WebMvcConfigurer scimWebMvcConfigurer() {
      return new WebMvcConfigurer() {
        @Override
        public void configureMessageConverters(HttpMessageConverters.ServerBuilder builder) {
          builder.addCustomConverter(new ScimJsonHttpMessageConverter());
        }
      };
    }
  }
}
