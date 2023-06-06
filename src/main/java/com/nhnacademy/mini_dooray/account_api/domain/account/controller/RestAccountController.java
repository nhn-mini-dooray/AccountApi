package com.nhnacademy.mini_dooray.account_api.domain.account.controller;

import com.nhnacademy.mini_dooray.account_api.domain.account.model.request.CreateAccountRequestDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.request.EmailRequestDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.response.FindByEmailResponseDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.service.AccountService;
import com.nhnacademy.mini_dooray.account_api.domain.status.model.request.StatusCodeRequestDto;
import com.nhnacademy.mini_dooray.account_api.exception.ValidationFailedException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@RequestMapping("/accounts")
public class RestAccountController {
    private final AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public ResponseEntity<Void> signupHandler(@Valid @RequestBody CreateAccountRequestDto responseDto,
                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        accountService.createAccount(responseDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{id}/dormant")
    public ResponseEntity<Void> dormantHandler(@PathVariable(name = "id") Long id,
                                               @RequestBody StatusCodeRequestDto status,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        accountService.updateAccount(id, status);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}/withdrawal")
    public ResponseEntity<Void> withdrawalHandler(@PathVariable(name = "id") Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/email")
    public ResponseEntity<FindByEmailResponseDto> emailHandlers(@RequestBody EmailRequestDto emailRequestDto){
        FindByEmailResponseDto findByEmailResponseDto = accountService.findAccountByEmail(emailRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(findByEmailResponseDto);
    }

}
