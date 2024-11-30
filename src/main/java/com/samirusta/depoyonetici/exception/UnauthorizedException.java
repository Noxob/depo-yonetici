package com.samirusta.depoyonetici.exception;

import lombok.Data;

@Data
public class UnauthorizedException extends Exception{
    private String message;
    public UnauthorizedException(){
        super();
    }

    public UnauthorizedException(String message){
        super(message);
    }


}
