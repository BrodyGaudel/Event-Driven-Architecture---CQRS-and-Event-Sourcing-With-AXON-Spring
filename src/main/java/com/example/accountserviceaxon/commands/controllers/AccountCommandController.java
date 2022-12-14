package com.example.accountserviceaxon.commands.controllers;

import com.example.accountserviceaxon.commonapi.commands.CreateAccountCommand;
import com.example.accountserviceaxon.commonapi.commands.CreditAccountCommand;
import com.example.accountserviceaxon.commonapi.commands.DebitAccountCommand;
import com.example.accountserviceaxon.commonapi.dto.CreateAccountRequestDTO;
import com.example.accountserviceaxon.commonapi.dto.CreditAccountRequestDTO;
import com.example.accountserviceaxon.commonapi.dto.DebitAccountRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping("/commands/account")
public class AccountCommandController {

    private CommandGateway commandGateway;
    private EventStore eventStore;

    public AccountCommandController(CommandGateway commandGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }

    @PostMapping("/create")
    public CompletableFuture<String> createNewAccount(@RequestBody CreateAccountRequestDTO createAccountRequestDTO){

        return commandGateway.send(new CreateAccountCommand(
                UUID.randomUUID().toString(),
                createAccountRequestDTO.getCurrency(),
                createAccountRequestDTO.getInitialBalance()
        ));

    }

    @PostMapping("/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDTO debitAccountRequestDTO){

        return commandGateway.send(new DebitAccountCommand(
                debitAccountRequestDTO.getAccountId(),
                debitAccountRequestDTO.getCurrency(),
                debitAccountRequestDTO.getAmount()
        ));

    }

    @PostMapping("/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDTO creditAccountRequestDTO){

        return commandGateway.send(new CreditAccountCommand(
                creditAccountRequestDTO.getAccountId(),
                creditAccountRequestDTO.getCurrency(),
                creditAccountRequestDTO.getAmount()
        ));

    }

    @GetMapping("/eventStore/{id}")
    public Stream eventStore(@PathVariable(name = "id") String id){
        return eventStore.readEvents(id).asStream();
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
