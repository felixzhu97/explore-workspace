package com.explore.provisioning;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@DisplayName("ProvisioningAutoConfiguration")
class ProvisioningAutoConfigurationTest {

  private static final AutoConfigurations CONFIG =
      AutoConfigurations.of(ProvisioningAutoConfiguration.class);

  @Test
  @DisplayName("should register the converter configurer and filter translator in servlet apps")
  void shouldRegisterTheConverterConfigurerAndFilterTranslatorInServletApps() {
    new WebApplicationContextRunner()
        .withConfiguration(CONFIG)
        .run(
            context -> {
              assertThat(context).hasSingleBean(ScimFilterTranslator.class);
              assertThat(context).hasBean("scimWebMvcConfigurer");
              assertThat(context.getBean("scimWebMvcConfigurer"))
                  .isInstanceOf(WebMvcConfigurer.class);
            });
  }

  @Test
  @DisplayName("should register only the filter translator outside servlet apps")
  void shouldRegisterOnlyTheFilterTranslatorOutsideServletApps() {
    new ApplicationContextRunner()
        .withConfiguration(CONFIG)
        .run(
            context -> {
              assertThat(context).hasSingleBean(ScimFilterTranslator.class);
              assertThat(context).doesNotHaveBean("scimWebMvcConfigurer");
            });
  }
}
