package com.example.accountserviceaxon.commonapi.commands;

import lombok.Getter;

import java.math.BigDecimal;

public class CreateAccountCommand extends BaseCommand<String> {

    @Getter
    private String currency;

    @Getter
    private BigDecimal initialBalance;

    public CreateAccountCommand(String id, String currency, BigDecimal initialBalance) {
        super(id);
        this.currency = currency;
        this.initialBalance = initialBalance;
    }

}
