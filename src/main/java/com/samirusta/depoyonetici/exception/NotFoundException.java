package com.samirusta.depoyonetici.exception;

public class NotFoundException extends Exception{
    private String message;
    public NotFoundException(){
        super();
    }

    public NotFoundException(String message){
        super(message);
    }
}
