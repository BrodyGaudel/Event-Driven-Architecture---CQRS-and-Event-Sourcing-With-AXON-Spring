package com.example.accountserviceaxon.query.repositories;

import com.example.accountserviceaxon.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
