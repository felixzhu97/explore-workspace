package com.finpulse.server.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PaymentRequest {
  @NotNull UUID accountId;
  String counterparty;
  @NotNull BigDecimal amount;
  @NotBlank String currency;
  @NotBlank String status;
}
