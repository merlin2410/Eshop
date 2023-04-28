package com.Merlin.eshop.exception;

public class MobileNumberOrEmailAlreadyRegisteredException extends Exception{
    public MobileNumberOrEmailAlreadyRegisteredException(String message){
        super(message);
    }
}
