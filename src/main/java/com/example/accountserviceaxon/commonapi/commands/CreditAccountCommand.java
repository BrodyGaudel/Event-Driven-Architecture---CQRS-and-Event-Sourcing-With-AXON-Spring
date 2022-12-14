package com.example.accountserviceaxon.commonapi.commands;

import lombok.Getter;

import java.math.BigDecimal;

public class CreditAccountCommand extends BaseCommand<String>{

    @Getter
    private String currency;

    @Getter
    private BigDecimal amount;

    public CreditAccountCommand(String id, String currency, BigDecimal amount) {
        super(id);
        this.currency = currency;
        this.amount = amount;
    }

}
