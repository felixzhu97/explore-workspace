package com.finpulse.server.position.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PositionRequest {
  @NotNull UUID portfolioId;
  @NotNull UUID instrumentId;
  @NotNull BigDecimal quantity;
  BigDecimal costBasis;
}
