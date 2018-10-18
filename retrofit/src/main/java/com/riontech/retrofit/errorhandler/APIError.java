package com.riontech.retrofit.errorhandler;

/**
 * Created by Vaghela Mithun R. on 30-Jun-17.
 * vaghela.mithun@gmail.com
 */
public class APIError {
    private int statusCode;
    private String message;

    public APIError() {
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
