package com.nhnacademy.mini_dooray.account_api.domain.status.model.type_code;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StatusCode {
    ACTIVE("활성화"),
    DORMANT("비활성화");
    private final String demonstration;
}
