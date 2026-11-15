package com.ai.testsupport;

import com.ai.common.controller.AnonymousId;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

/** Attaches a resolved client id to MockMvc requests (simulates {@link AnonymousId} filter). */
public final class AnonymousIdRequestPostProcessor {

  private AnonymousIdRequestPostProcessor() {}

  /** Returns a post-processor that sets the client id request attribute. */
  public static RequestPostProcessor withClientId(String ownerId) {
    return request -> {
      if (request instanceof MockHttpServletRequest mock) {
        mock.setAttribute(AnonymousId.REQUEST_ATTRIBUTE, ownerId);
      }
      return request;
    };
  }
}
