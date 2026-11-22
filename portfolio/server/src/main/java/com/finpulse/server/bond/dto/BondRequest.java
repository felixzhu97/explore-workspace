package com.finpulse.server.bond.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class BondRequest {
  @NotNull UUID instrumentId;
  BigDecimal faceValue;
  BigDecimal couponRate;
  BigDecimal ytm;
  BigDecimal duration;
  BigDecimal convexity;
  BigDecimal maturityYears;
  Integer frequency;
}
