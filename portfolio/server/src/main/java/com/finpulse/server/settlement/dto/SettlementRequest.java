package com.finpulse.server.settlement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class SettlementRequest {
  @NotNull UUID tradeId;
  @NotNull UUID paymentId;
  @NotBlank String status;
  Instant settledAt;
}
