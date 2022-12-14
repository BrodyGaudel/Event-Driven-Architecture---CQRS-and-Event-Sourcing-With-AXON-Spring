package com.example.accountserviceaxon.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditAccountRequestDTO {
    private String accountId;
    private String currency;
    private BigDecimal amount;
}
