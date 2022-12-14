package com.example.accountserviceaxon.commonapi.events;

import lombok.Getter;

import java.math.BigDecimal;

public class AccountCreditedEvent extends BaseEvent<String>{

    @Getter
    private String currency;
    @Getter
    private BigDecimal amount;

    public AccountCreditedEvent(String id, String currency, BigDecimal amount) {
        super(id);
        this.currency = currency;
        this.amount = amount;
    }
}
