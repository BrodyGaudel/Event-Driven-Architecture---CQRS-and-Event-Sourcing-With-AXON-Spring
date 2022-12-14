package com.example.accountserviceaxon.query.entities;

import com.example.accountserviceaxon.commonapi.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    private String id;
    private Instant createdAt;
    private BigDecimal balance;
    private AccountStatus status;
    private String currency;

    @OneToMany(mappedBy = "account")
    private List<AccountTransaction> transactions;
}
