package com.nhnacademy.mini_dooray.account_api.advice;

import com.nhnacademy.mini_dooray.account_api.advice.error_response.ErrorResponse;
import com.nhnacademy.mini_dooray.account_api.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestAccountAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> NotFoundExceptionHandler(NotFoundException exception) {
        log.info("advice_Error_Message: {}", exception.getMessage(), exception);

        ErrorResponse response =
                new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), "Bad request");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = {
            EmptyResultDataAccessException.class,
            ValidationFailedException.class})
    public ResponseEntity<ErrorResponse> EmptyResultDataAccessExceptionHandler(EmptyResultDataAccessException exception) {
        log.info("advice_Error_Message: {}", exception.getMessage(), exception);

        ErrorResponse response =
                new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), "Bad request");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = {
            DuplicateLoginIdException.class,
            DuplicateEmailException.class})
    public ResponseEntity<String> httpResponseExceptionHandler(Exception exception) {
        log.error("advice_Error_Message: {}", exception.getMessage());

        return ResponseEntity
                .badRequest()
                .body(exception.getMessage());
    }

}
