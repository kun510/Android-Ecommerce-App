package com.example.model;

import java.util.List;

public class loaispModel {
    boolean succes;
    String message;
    List<Loaisp> result;

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Loaisp> getResult() {
        return result;
    }

    public void setResult(List<Loaisp> result) {
        this.result = result;
    }
}
