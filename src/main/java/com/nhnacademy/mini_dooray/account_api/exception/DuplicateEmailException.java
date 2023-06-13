package com.nhnacademy.mini_dooray.account_api.exception;

public class DuplicateEmailException extends HttpResponseException {
    public DuplicateEmailException() {
        super("이미 존재하는 이메일입니다.");
    }
}
