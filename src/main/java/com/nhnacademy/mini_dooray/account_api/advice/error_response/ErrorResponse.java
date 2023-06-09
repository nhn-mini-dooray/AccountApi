package com.nhnacademy.mini_dooray.account_api.advice.error_response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
    private String error;
}
