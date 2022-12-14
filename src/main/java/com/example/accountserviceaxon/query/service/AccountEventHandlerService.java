package com.example.accountserviceaxon.query.service;

import com.example.accountserviceaxon.commonapi.events.AccountCreatedEvent;
import com.example.accountserviceaxon.query.entities.Account;
import com.example.accountserviceaxon.query.queries.GetAllAccounts;
import com.example.accountserviceaxon.query.repositories.AccountRepository;
import com.example.accountserviceaxon.query.repositories.AccountTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@Slf4j
public class AccountEventHandlerService {
    private AccountRepository accountRepository;
    private AccountTransactionRepository transactionRepository;

    public AccountEventHandlerService(AccountRepository accountRepository,
                                      AccountTransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @EventHandler
    public void on(AccountCreatedEvent event, EventMessage<AccountCreatedEvent> eventMessage){
        log.info("*****************************");
        log.info("AccountRepository received");
        Account account = new Account();
        account.setId(event.getId());
        account.setBalance(event.getBalance());
        account.setStatus(event.getStatus());
        account.setCurrency(event.getCurrency());
        account.setCreatedAt(eventMessage.getTimestamp());
        accountRepository.save(account);
    }

    @QueryHandler
    public List<Account> on(GetAllAccounts query){
        return accountRepository.findAll();
    }
}
