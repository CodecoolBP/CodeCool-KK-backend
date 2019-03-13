package com.codecool.cckk.model;

public class ReturnMessage {
    private boolean success;
    private String message;

    public ReturnMessage(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ReturnMessage() {
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
