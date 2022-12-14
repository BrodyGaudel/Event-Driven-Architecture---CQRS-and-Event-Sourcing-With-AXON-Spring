package com.example.accountserviceaxon.commonapi.exceptions;

public class NegativeBalanceException extends RuntimeException{

    public NegativeBalanceException(String message){
        super(message);
    }
}
