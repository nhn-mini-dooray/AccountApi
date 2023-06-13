package com.nhnacademy.mini_dooray.account_api.exception;

public class DuplicateLoginIdException extends HttpResponseException {
    public DuplicateLoginIdException() {
        super("이미 존재하는 id입니다.");
    }
}
