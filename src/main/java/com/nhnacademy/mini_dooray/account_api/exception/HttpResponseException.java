package com.nhnacademy.mini_dooray.account_api.exception;

public class HttpResponseException extends RuntimeException {
    public HttpResponseException(String message) {
        super(message);
    }
}
