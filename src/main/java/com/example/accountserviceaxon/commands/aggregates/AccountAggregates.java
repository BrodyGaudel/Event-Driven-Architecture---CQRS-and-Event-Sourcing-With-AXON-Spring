package com.example.accountserviceaxon.commands.aggregates;

import com.example.accountserviceaxon.commonapi.commands.CreateAccountCommand;
import com.example.accountserviceaxon.commonapi.commands.CreditAccountCommand;
import com.example.accountserviceaxon.commonapi.commands.DebitAccountCommand;
import com.example.accountserviceaxon.commonapi.enums.AccountStatus;
import com.example.accountserviceaxon.commonapi.events.AccountCreatedEvent;
import com.example.accountserviceaxon.commonapi.events.AccountCreditedEvent;
import com.example.accountserviceaxon.commonapi.events.AccountDebitedEvent;
import com.example.accountserviceaxon.commonapi.exceptions.NegativeBalanceException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

/**
 * C'est un objet qui ressume l'état de notre application (snapshot)
 */
@Aggregate
public class AccountAggregates {

    @AggregateIdentifier
    private String accountId;
    private String currency;
    private BigDecimal balance;
    private AccountStatus status;

    public AccountAggregates() {
        //required by AXON
    }

    /**
     * fonction de décision
     * Contrusteur qui va etre appelé au moment où
     * on va recevoir une commande de type CreateAccountCommand
     * @param createAccountCommand
     */
    @CommandHandler
    public AccountAggregates(CreateAccountCommand createAccountCommand) {
        int k = createAccountCommand.getInitialBalance().compareTo(new BigDecimal(0));
        if(k==-1){
            throw new NegativeBalanceException("Negative balance");
        }
        //publication de l'évènement
        AggregateLifecycle.apply(new AccountCreatedEvent(
                createAccountCommand.getId(),
                createAccountCommand.getCurrency(),
                createAccountCommand.getInitialBalance(),
                AccountStatus.CREATED
        ));
    }

    /**
     * fonction d'évolution de application
     * @param event
     */
    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.accountId = event.getId();
        this.balance = event.getBalance();
        this.currency = event.getCurrency();
        this.status = event.getStatus();
    }

    @CommandHandler
    public void handle(CreditAccountCommand command){
        int k = command.getAmount().compareTo(new BigDecimal(0));
        if(k==-1){
            throw new NegativeBalanceException("Negative balance");
        }
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getCurrency(),
                command.getAmount()
        ));
    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        BigDecimal tmp = this.balance;
        this.balance = event.getAmount().add(tmp);
    }

    @CommandHandler
    public void handle(DebitAccountCommand command){
        BigDecimal tmp = this.balance;
        int j = command.getAmount().compareTo(tmp);
        int i = command.getAmount().compareTo(new BigDecimal(0));
        if(i==-1){
            throw new NegativeBalanceException("Negative balance");
        }
        if(j==1){
            throw new RuntimeException("Solde Insuffisant");
        }
        AggregateLifecycle.apply(new AccountDebitedEvent(
                command.getId(),
                command.getCurrency(),
                command.getAmount()
        ));
    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent event){
        BigDecimal tmp = this.balance;
        this.balance = tmp.subtract(event.getAmount());
    }


}
