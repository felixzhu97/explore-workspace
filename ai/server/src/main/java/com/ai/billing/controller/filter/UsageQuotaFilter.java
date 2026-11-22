package com.ai.billing.controller.filter;

import com.ai.billing.domain.model.QuotaDecision;
import com.explore.errors.Problems;
import com.ai.billing.domain.model.QuotaSubject;
import com.ai.billing.service.DailyUsageQuotaService;
import com.ai.common.controller.AnonymousId;
import com.ai.common.domain.model.OwnerId;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Free/Pro daily hard quota per Anonymous id (commercial cost guardrail).
 *
 * @see <a href="https://docs.stripe.com/billing/subscriptions/usage-based">Stripe usage-based
 *     billing</a>
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 28)
@RequiredArgsConstructor
public class UsageQuotaFilter extends OncePerRequestFilter {

  private static final List<String> METERED_PREFIXES =
      List.of(
          "/api/chat",
          "/api/text-analysis",
          "/api/rag",
          "/api/pipelines",
          "/api/automations",
          "/api/skills",
          "/api/tools",
          "/api/images",
          "/api/audio",
          "/api/vision",
          "/api/mcp",
          "/api/workflows",
          "/api/eval");

  private final DailyUsageQuotaService dailyUsageQuotaService;

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    if (!dailyUsageQuotaService.isEnabled()) {
      return true;
    }
    if (!HttpMethod.POST.name().equals(request.getMethod())) {
      return true;
    }
    String path = request.getRequestURI();
    if (path == null || !path.startsWith("/api/")) {
      return true;
    }
    return METERED_PREFIXES.stream().noneMatch(path::startsWith);
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    Object attr = request.getAttribute(AnonymousId.REQUEST_ATTRIBUTE);
    QuotaSubject owner =
        attr instanceof String id && !id.isBlank()
            ? QuotaSubject.createOwnerSubject(OwnerId.createClientKey(id))
            : null;
    QuotaDecision decision =
        dailyUsageQuotaService.tryConsume(owner, AnonymousId.resolveLimitAddress(request));
    response.setHeader("RateLimit-Limit", String.valueOf(decision.getLimit()));
    response.setHeader("RateLimit-Remaining", String.valueOf(decision.getRemaining()));
    response.setHeader("RateLimit-Policy", decision.getPlan().getValue());
    if (!decision.isAllowed()) {
      response.setStatus(429);
      response.setContentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE);
      response
          .getOutputStream()
          .write(
              """
                    {"type":"%s","title":"Too Many Requests",
                    "status":429,"detail":"Daily plan quota exceeded"}
                    """
                  .formatted(Problems.typeOf("QUOTA_EXCEEDED"))
                  .strip()
                  .getBytes(StandardCharsets.UTF_8));
      return;
    }
    filterChain.doFilter(request, response);
  }
}
