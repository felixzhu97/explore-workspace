package com.ai.chat.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ai.account.controller.OwnerContext;
import com.ai.account.service.OwnerErasureService;
import com.ai.common.controller.AnonymousIdCookieFactory;
import com.ai.common.domain.model.OwnerKey;
import com.ai.common.infra.config.AnonymousIdProperties;
import com.ai.testsupport.AnonymousIdRequestPostProcessor;
import com.ai.testsupport.SliceWebMvcTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

@SliceWebMvcTest(controllers = PrivacyController.class)
@Import(PrivacyControllerTest.CookieTestConfig.class)
@DisplayName("PrivacyController")
class PrivacyControllerTest {

  private static final OwnerKey OWNER =
      OwnerKey.createClientKey("11111111-1111-1111-1111-111111111111");

  @Autowired private MockMvcTester mvc;

  @MockitoBean private OwnerContext ownerContext;

  @MockitoBean private OwnerErasureService ownerErasureService;

  @Test
  @DisplayName("should erase all owner data")
  void shouldEraseAllOwnerDataWhenDeletePrivacySessions() {
    when(ownerContext.requireOwner(any())).thenReturn(OWNER);

    assertThat(
            mvc.delete()
                .uri("/api/privacy/sessions")
                .with(
                    AnonymousIdRequestPostProcessor.withClientId(
                        "c:11111111-1111-1111-1111-111111111111")))
        .hasStatus(HttpStatus.NO_CONTENT);
    verify(ownerErasureService).eraseAllForOwner(OWNER);
  }

  @Test
  @DisplayName("should rotate cookie when reset identity")
  void shouldRotateCookieWhenResetIdentity() {
    assertThat(mvc.post().uri("/api/privacy/reset-identity"))
        .hasStatus(HttpStatus.NO_CONTENT)
        .cookies()
        .containsCookie("explore_anonymous_id");
  }

  @TestConfiguration
  static class CookieTestConfig {

    @Bean
    AnonymousIdProperties clientIdentityProperties() {
      AnonymousIdProperties properties = new AnonymousIdProperties();
      properties.setCookieName("explore_anonymous_id");
      properties.setSecure(false);
      properties.setSameSite("Lax");
      return properties;
    }

    @Bean
    AnonymousIdCookieFactory clientIdentityCookieFactory(AnonymousIdProperties properties) {
      return new AnonymousIdCookieFactory(properties);
    }
  }
}
