package com.finpulse.server.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PortfolioRequest {
  @NotNull UUID accountId;
  @NotBlank String name;
  @NotBlank String baseCurrency;
}
