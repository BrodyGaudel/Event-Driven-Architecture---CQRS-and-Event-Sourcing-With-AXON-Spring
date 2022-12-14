package com.example.accountserviceaxon.commonapi.events;

import com.example.accountserviceaxon.commonapi.commands.BaseCommand;
import lombok.Getter;

public class BaseEvent<T> {

    @Getter
    private T id;

    public BaseEvent(T id){
        this.id = id;
    }
}
