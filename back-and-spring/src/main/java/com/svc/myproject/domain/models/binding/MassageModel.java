package com.svc.myproject.domain.models.binding;

public class MassageModel {
    private String message;

    public MassageModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
