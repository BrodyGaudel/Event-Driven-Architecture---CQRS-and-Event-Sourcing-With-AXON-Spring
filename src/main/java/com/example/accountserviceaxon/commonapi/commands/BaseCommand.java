package com.example.accountserviceaxon.commonapi.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * le type génerique T est crée dans le cas où les comptes ont les types d'id different
 * Une commande est un objet imuable d'où n'avons que des Getters
 * @param <T>
 */
public class BaseCommand<T> {


    @TargetAggregateIdentifier
    @Getter
    private T id;

    public BaseCommand(T id) {
        this.id = id;
    }
}
