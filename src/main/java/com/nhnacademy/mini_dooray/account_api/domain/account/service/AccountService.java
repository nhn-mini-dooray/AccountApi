package com.nhnacademy.mini_dooray.account_api.domain.account.service;

import com.nhnacademy.mini_dooray.account_api.domain.account.entity.Account;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.request.CreateAccountRequestDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.request.EmailRequestDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.request.LoginRequestDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.response.AccountLoginDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.response.EmailResponseDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.repository.AccountRepository;
import com.nhnacademy.mini_dooray.account_api.domain.status.entity.Status;
import com.nhnacademy.mini_dooray.account_api.domain.status.model.request.StatusCodeRequestDto;
import com.nhnacademy.mini_dooray.account_api.domain.status.model.type_code.StatusCode;
import com.nhnacademy.mini_dooray.account_api.domain.status.repository.StatusRepository;
import com.nhnacademy.mini_dooray.account_api.exception.DuplicateEmailException;
import com.nhnacademy.mini_dooray.account_api.exception.DuplicateLoginIdException;
import com.nhnacademy.mini_dooray.account_api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final StatusRepository statusRepository;

    @Transactional(readOnly = true)
    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void createAccount(CreateAccountRequestDto requestDto) {
        Status status = statusRepository.findByName(StatusCode.ACTIVE).orElseThrow(NotFoundException::new);
        validSignup(requestDto);
        Account account = new Account(status, requestDto.getLoginId(), requestDto.getEmail(), requestDto.getPassword());
        accountRepository.save(account);
    }

    private void validSignup(CreateAccountRequestDto requestDto) {
        if (Boolean.TRUE.equals(accountRepository.existsByLoginId(requestDto.getLoginId()))) {
            throw new DuplicateLoginIdException();
        }

        if (Boolean.TRUE.equals(accountRepository.existsByEmail(requestDto.getEmail()))) {
            throw new DuplicateEmailException();
        }
    }

    public void updateAccount(Long id, StatusCodeRequestDto statusCode) {
        Status status = statusRepository.findByName(statusCode.getStatus()).orElseThrow(NotFoundException::new);
        Account account = accountRepository.findById(id).orElseThrow(NotFoundException::new);
        account.setStatus(status);
        accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public EmailResponseDto findAccountByEmail(EmailRequestDto email) {
        return accountRepository.findByEmail(email.getEmail())
                .orElseThrow(NotFoundException::new);
    }

    @Transactional(readOnly = true)
    public AccountLoginDto findAccountByLoginId(LoginRequestDto loginRequestDto) {
        return accountRepository.findLoginDtoByLoginId(loginRequestDto.getLoginId())
                .orElseThrow(NotFoundException::new);
    }

}
