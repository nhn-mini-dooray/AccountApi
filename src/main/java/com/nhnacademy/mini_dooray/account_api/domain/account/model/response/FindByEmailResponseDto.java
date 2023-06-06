package com.nhnacademy.mini_dooray.account_api.domain.account.model.response;

import com.nhnacademy.mini_dooray.account_api.domain.status.entity.Status;

import java.time.LocalDate;

public interface FindByEmailResponseDto {
    Long getAccountId();
    Status getStatus();
    String getLoginId();
    String getPassword();
    LocalDate getLastLoginDate();


}