package com.svc.myproject.domain.models.services;

public class EmailResponseModel {
    private int cod;
    private String message;

    public EmailResponseModel() {
    }

    public EmailResponseModel(int cod, String message) {
        this.cod = cod;
        this.message = message;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EmailResponseModel{" +
                "cod=" + cod +
                ", message='" + message + '\'' +
                '}';
    }
}
