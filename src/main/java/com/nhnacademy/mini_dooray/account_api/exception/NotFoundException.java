package com.nhnacademy.mini_dooray.account_api.exception;

public class NotFoundException extends IllegalArgumentException {
    public NotFoundException() {
        super("값을 찾을수 없습니다.");
    }
}
