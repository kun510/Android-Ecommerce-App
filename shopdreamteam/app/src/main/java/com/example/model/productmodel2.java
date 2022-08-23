package com.example.model;

import java.util.List;

public class productmodel2 {
    boolean succes;
    String message;
    List<productModel> result;

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

    public List<productModel> getResult() {
        return result;
    }

    public void setResult(List<productModel> result) {
        this.result = result;
    }
}
