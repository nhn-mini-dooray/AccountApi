package com.nhnacademy.mini_dooray.account_api.domain.account.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountLoginDto {
    private final Long accountId;
    private final String loginId;
    private final String password;
}
