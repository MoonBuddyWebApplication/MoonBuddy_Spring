package com.project.moonbuddy.exception;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public abstract class MoonBuddyException extends RuntimeException{
    public final Map<String, String> validation = new HashMap<>();

    public MoonBuddyException(String message){
        super(message);
    }
    public MoonBuddyException(String message, Throwable cause){
        super(message, cause);
    }
    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message){
        validation.put(fieldName, message);
    }
}
