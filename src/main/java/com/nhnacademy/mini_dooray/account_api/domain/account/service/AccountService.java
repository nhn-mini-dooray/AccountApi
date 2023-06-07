package com.nhnacademy.mini_dooray.account_api.domain.account.service;

import com.nhnacademy.mini_dooray.account_api.domain.account.entity.Account;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.request.CreateAccountRequestDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.request.EmailRequestDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.response.FindByEmailResponseDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.repository.AccountRepository;
import com.nhnacademy.mini_dooray.account_api.domain.status.entity.Status;
import com.nhnacademy.mini_dooray.account_api.domain.status.model.request.StatusCodeRequestDto;
import com.nhnacademy.mini_dooray.account_api.domain.status.model.type_code.StatusCode;
import com.nhnacademy.mini_dooray.account_api.domain.status.repository.StatusRepository;
import com.nhnacademy.mini_dooray.account_api.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final StatusRepository statusRepository;

    @Transactional(readOnly = true)
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public void createAccount(CreateAccountRequestDto requestDto) {
        Status status = statusRepository.findByName(StatusCode.ACTIVE).orElseThrow(NotFoundException::new);

        Account account = new Account(status, requestDto.getLoginId(), requestDto.getEmail(), requestDto.getPassword());
        accountRepository.save(account);
    }

    public void updateAccount(Long id, StatusCodeRequestDto statusCode) { //statusCode가 잘 작동되는지 확인
        Status status = statusRepository.findByName(statusCode.getStatus()).orElseThrow(NotFoundException::new);
        Account account = accountRepository.findById(id).orElseThrow(NotFoundException::new);
        account.setStatus(status);
        accountRepository.save(account);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public FindByEmailResponseDto findAccountByEmail(EmailRequestDto email) {
        return accountRepository.findByEmail(email.getEmail());
    }


}
