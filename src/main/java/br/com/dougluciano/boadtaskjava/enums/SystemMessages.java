package br.com.dougluciano.boadtaskjava.enums;

import lombok.Getter;


public enum SystemMessages {

    USER_NOT_FOUND("User not found!");


    private String message;

    SystemMessages(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
