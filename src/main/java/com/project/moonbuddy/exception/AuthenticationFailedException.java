package com.project.moonbuddy.exception;

public class AuthenticationFailedException extends MoonBuddyException{
    @Override
    public int getStatusCode() {
        return 400;
    }

    public AuthenticationFailedException(String message){
        super(message);
    }
}
