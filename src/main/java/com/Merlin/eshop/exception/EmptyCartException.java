package com.Merlin.eshop.exception;

public class EmptyCartException extends Exception{
    public EmptyCartException(String message){
        super(message);
    }
}
