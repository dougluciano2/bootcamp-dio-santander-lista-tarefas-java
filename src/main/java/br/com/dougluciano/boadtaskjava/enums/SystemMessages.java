package br.com.dougluciano.boadtaskjava.enums;

import lombok.Getter;


public enum SystemMessages {

    USER_NOT_FOUND("User not found!"),
    TASK_NOT_FOUND("Task not found!");


    private String message;

    SystemMessages(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
