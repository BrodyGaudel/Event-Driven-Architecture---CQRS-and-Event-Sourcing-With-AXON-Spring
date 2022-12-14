package com.example.accountserviceaxon.commonapi.events;

import com.example.accountserviceaxon.commonapi.enums.AccountStatus;
import lombok.Getter;

import java.math.BigDecimal;

public class AccountCreatedEvent extends BaseEvent<String>{

    @Getter
    private String currency;

    @Getter
    private BigDecimal balance;

    @Getter
    private AccountStatus status;

    public AccountCreatedEvent(String id) {
        super(id);
    }

    public AccountCreatedEvent(String id, String currency, BigDecimal balance, AccountStatus status) {
        super(id);
        this.currency = currency;
        this.balance = balance;
        this.status = status;
    }
}
