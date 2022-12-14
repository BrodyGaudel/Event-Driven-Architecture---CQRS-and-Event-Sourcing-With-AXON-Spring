package com.example.accountserviceaxon.query.repositories;

import com.example.accountserviceaxon.query.entities.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {
}
