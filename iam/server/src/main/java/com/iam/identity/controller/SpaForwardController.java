package com.iam.identity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Serves the Angular SPA for browser routes (sign-in + client registration). */
@Controller
public class SpaForwardController {

  /**
   * Forwards browser console routes to the Angular {@code index.html}.
   *
   * @return forward view name
   */
  @GetMapping({
    "/",
    "/sign-in",
    "/sign-in/verify",
    "/sign-in/ott",
    "/sign-in/ott/sent",
    "/sign-in/totp",
    "/oauth-clients",
    "/oauth-clients/**",
    "/clients",
    "/clients/**",
    "/users",
    "/security/passkeys",
    "/security/totp"
  })
  public String forwardSpaRoutes() {
    return "forward:/index.html";
  }
}
